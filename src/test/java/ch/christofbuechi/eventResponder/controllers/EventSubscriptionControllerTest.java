package ch.christofbuechi.eventResponder.controllers;

import ch.christofbuechi.eventResponder.domain.Scope;
import ch.christofbuechi.eventResponder.domain.SubscribeToEventsFromClientRequest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

public class EventSubscriptionControllerTest {

    @Test
    public void testRestClient() {
    EventSubscriptionController controller = new EventSubscriptionController();
        SubscribeToEventsFromClientRequest request = new SubscribeToEventsFromClientRequest();
        request.setApiKey("");
        request.setServerUrl("");
        request.setWebHook("https://google.com");
        request.setScope(Scope.mobileApp);
        request.setScopedObjectId(31009056);
        request.setRoutingKey(Collections.singletonList("MobileAppRegistrationChange.*"));
        ResponseEntity<String> result = controller.callBackend(request);
        System.out.println(result);


    }

}