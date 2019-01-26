package ch.christofbuechi.eventResponder

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
object EventResponderApplication {

    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(EventResponderApplication::class.java, *args)
    }

}

