package ch.christofbuechi.eventResponder.domain;

public class SubscribeToEventsResponseWrapper {

    @com.fasterxml.jackson.annotation.JsonProperty("subscribeToEventsResponse")
    private SubscribeToEventsResponse subscribeToEventsResponse;

    public SubscribeToEventsResponse getSubscribeToEventsResponse() {
        return subscribeToEventsResponse;
    }

    public void setSubscribeToEventsResponse(SubscribeToEventsResponse subscribeToEventsResponse) {
        this.subscribeToEventsResponse = subscribeToEventsResponse;
    }

    @Override
    public String toString() {
        return "SubscribeToEventsResponseWrapper{" +
                "subscribeToEventsResponse=" + subscribeToEventsResponse +
                '}';
    }
}
