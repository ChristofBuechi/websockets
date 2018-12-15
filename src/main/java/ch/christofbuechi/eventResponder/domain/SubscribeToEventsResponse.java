package ch.christofbuechi.eventResponder.domain;


public class SubscribeToEventsResponse {
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEventSubscriptionId() {
        return eventSubscriptionId;
    }

    public void setEventSubscriptionId(String eventSubscriptionId) {
        this.eventSubscriptionId = eventSubscriptionId;
    }

    Status status;
    String eventSubscriptionId;

    @Override
    public String toString() {
        return "SubscribeToEventsResponse{" +
                "status=" + status +
                ", eventSubscriptionId='" + eventSubscriptionId + '\'' +
                '}';
    }
}
