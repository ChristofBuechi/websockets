package ch.christofbuechi.eventResponder.domain;

import java.util.ArrayList;
import java.util.List;

public class SubscribeToEventsFromClientRequest {

    private String apiKey;
    private String serverUrl;
    private String webHook;
    private Scope scope;
    private Integer scopedObjectId;
    private List<String> routingKey = new ArrayList<>();

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getWebHook() {
        return webHook;
    }

    public void setWebHook(String webHook) {
        this.webHook = webHook;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public Integer getScopedObjectId() {
        return scopedObjectId;
    }

    public void setScopedObjectId(int scopedObjectId) {
        this.scopedObjectId = scopedObjectId;
    }

    public List<String> getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(List<String> routingKey) {
        this.routingKey = routingKey;
    }

    public void extendWebHook(String generatedUUID) {
        webHook = webHook + "/" + generatedUUID;
    }
}
