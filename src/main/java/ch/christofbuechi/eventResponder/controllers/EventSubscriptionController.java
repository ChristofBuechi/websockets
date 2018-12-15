package ch.christofbuechi.eventResponder.controllers;

import ch.christofbuechi.eventResponder.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.UUID;

import static ch.christofbuechi.eventResponder.Constants.SECURED_CHAT_SPECIFIC_USER;

@RestController
public class EventSubscriptionController {

    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate;

   @PostMapping(value = "/events/{uuid}")
    public ResponseEntity< String > receiveEvent(@RequestBody WebHookResponse webHookResponse, @PathVariable String uuid) {

       //send webHookResponse to the correct receiver based on uuid
       brokerMessagingTemplate.convertAndSendToUser(uuid, SECURED_CHAT_SPECIFIC_USER, webHookResponse.toString());

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


    @PostMapping(value = "/subscribe")
    ResponseEntity<SubscribeToEventsFromClientResponse> callBackend(@RequestBody SubscribeToEventsFromClientRequest subscribeToEventsFromClientRequest) {
        String generatedUUID = UUID.randomUUID().toString();

        subscribeToEventsFromClientRequest.extendWebHook("/events/" + generatedUUID);

        SubscribeToEventsResponse responseId = createWebHookSubscription(subscribeToEventsFromClientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SubscribeToEventsFromClientResponse(generatedUUID, responseId));
    }

    private static SubscribeToEventsResponse createWebHookSubscription(SubscribeToEventsFromClientRequest eventSubscriptionFromClient) {
        RestTemplate restTemplate = new RestTemplate();

        final String uri = eventSubscriptionFromClient.getServerUrl() + "/public/eventsystem/SubscribeToEvents";

        SubscribeToEventsRequestWrapper wrapper = new SubscribeToEventsRequestWrapper(eventSubscriptionFromClient);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("APIKEY", eventSubscriptionFromClient.getApiKey());
        HttpEntity<SubscribeToEventsRequestWrapper> entity = new HttpEntity<>(wrapper, headers);

        ResponseEntity<SubscribeToEventsResponseWrapper> result = restTemplate.exchange(uri, HttpMethod.POST, entity, SubscribeToEventsResponseWrapper.class);

        return result.getBody().getSubscribeToEventsResponse();
    }

}
