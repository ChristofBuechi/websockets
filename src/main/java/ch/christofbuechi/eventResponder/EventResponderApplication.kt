package ch.christofbuechi.eventResponder

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.channel.PublishSubscribeChannel
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.file.dsl.Files
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHandler
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.publisher.SynchronousSink
import java.io.File
import java.time.Duration
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Consumer

@SpringBootApplication
class EventResponderApplication

fun main(args: Array<String>) {
    SpringApplication.run(EventResponderApplication::class.java, *args)
}

@Configuration
class WebSocketConfiguration {

    @Bean
    fun incomingFileFlow(@Value("file://\${HOME}/Desktop/in") f: File) =
            IntegrationFlows
                    .from(
                            Files
                                    .inboundAdapter(f)
                                    .autoCreateDirectory(true),
                            { p -> p.poller({ pm -> pm.fixedRate(1000) }) })
                    .channel(incomingFilesChannel())
                    .get()

    @Bean
    fun incomingFilesChannel() = PublishSubscribeChannel()

    @Bean
    fun wsha() = WebSocketHandlerAdapter()

    @Bean
    fun hm(): HandlerMapping {
        val suhm = SimpleUrlHandlerMapping()
        suhm.order = 10
        suhm.urlMap = mapOf("/ws/time" to webSocketHandlerTime(), "/ws/files" to wsh())
        return suhm
    }

    @Bean
    fun webSocketHandlerTime(): WebSocketHandler {
        return WebSocketHandler { session ->
            val om = ObjectMapper()
            val publisher = Flux
                    .generate(Consumer<SynchronousSink<FileEvent>> { sink ->
                        sink.next(FileEvent("${System.currentTimeMillis()}", "a/b/c"))
                    })
                    .map { om.writeValueAsString(it) }
                    .map { session.textMessage(it) }
                    .delayElements(Duration.ofSeconds(1L))
            session.send(publisher)
        }
    }


    @Bean
    fun wsh(): WebSocketHandler {
        val om = ObjectMapper()
        val connections = ConcurrentHashMap<String, MessageHandler?>()

        class ForwardingMessageHandler(val session: WebSocketSession,
                                       val sink: FluxSink<WebSocketMessage>) : MessageHandler {
            private val sessionId = session.id

            override fun handleMessage(msg: Message<*>) {
                val payload = msg.payload as File
                val fe = FileEvent(sessionId = sessionId, path = payload.absolutePath)
                val str = om.writeValueAsString(fe)
                val tm = session.textMessage(str)
                sink.next(tm)

            }
        }


        return WebSocketHandler { session ->
            val publisher = Flux
                    .create(Consumer<FluxSink<WebSocketMessage>> { sink ->
                        connections[session.id] = ForwardingMessageHandler(session, sink)
                        connections[session.id]?.let { incomingFilesChannel().subscribe(it) }
                    })
                    .doFinally {
                        connections[session.id]?.let { it1 -> incomingFilesChannel().unsubscribe(it1) }
                        connections.remove(session.id)
                    }
            session.send(publisher)
        }
    }
}

    data class FileEvent(val sessionId: String, val path: String)
