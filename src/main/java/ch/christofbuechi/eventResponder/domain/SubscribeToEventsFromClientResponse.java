package ch.christofbuechi.eventResponder.domain;

import ch.christofbuechi.eventResponder.domain.SubscribeToEventsResponse;

public class SubscribeToEventsFromClientResponse {
    private final String generatedUUID;
    private final SubscribeToEventsResponse subscriptionId;

    public SubscribeToEventsFromClientResponse(String generatedUUID, SubscribeToEventsResponse subscriptionId) {
        this.generatedUUID = generatedUUID;
        this.subscriptionId = subscriptionId;
    }


    public String getGeneratedUUID() {
        return generatedUUID;
    }

    public SubscribeToEventsResponse getSubscriptionId() {
        return subscriptionId;
    }
}
