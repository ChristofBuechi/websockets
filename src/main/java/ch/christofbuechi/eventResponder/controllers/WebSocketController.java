package ch.christofbuechi.eventResponder.controllers;

import ch.christofbuechi.eventResponder.domain.WebHookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

import static ch.christofbuechi.eventResponder.Constants.*;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate template;

    @Autowired
    WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    //Possible not needed
    @MessageMapping(SECURED_CHAT_ROOM)
    public void onReceivedMesage(@Payload WebHookResponse webHookResponse, Principal user, @Header("simpSessionId") String sessionId) {
//        this.template.convertAndSendToUser(user, "/secured/user/queue/specific-user", webHookResponse.toString());
        System.out.println("Sent Message: " + webHookResponse.toString());
    }
}