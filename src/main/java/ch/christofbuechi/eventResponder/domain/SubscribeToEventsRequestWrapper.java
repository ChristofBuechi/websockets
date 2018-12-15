package ch.christofbuechi.eventResponder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscribeToEventsRequestWrapper {

    @JsonProperty("subscribeToEventsRequest")
    private SubscribeToEventsRequest subscribeToEventsRequest;


    public SubscribeToEventsRequestWrapper(SubscribeToEventsFromClientRequest subscribeToEventsRequest) {
        this.subscribeToEventsRequest = new SubscribeToEventsRequest(subscribeToEventsRequest);
    }

    public SubscribeToEventsRequest getSubscribeToEventsRequest() {
        return subscribeToEventsRequest;
    }
}
