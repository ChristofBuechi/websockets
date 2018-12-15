package ch.christofbuechi.eventResponder.domain;

import java.util.List;

public class SubscribeToEventsRequest {

    private String webHook;
    private Scope scope;
    private Integer scopedObjectId;
    private List<String> routingKey;

    public SubscribeToEventsRequest(SubscribeToEventsFromClientRequest clientRequest) {
//        this.webHook = clientRequest.getWebHook();
        this.scope = clientRequest.getScope();
        this.routingKey = clientRequest.getRoutingKey();
        this.scopedObjectId = clientRequest.getScopedObjectId();
    }

    public String getWebHook() {
        return webHook;
    }

    public Scope getScope() {
        return scope;
    }

    public Integer getScopedObjectId() {
        return scopedObjectId;
    }

    public List<String> getRoutingKey() {
        return routingKey;
    }
}
