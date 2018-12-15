package ch.christofbuechi.eventResponder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {

    @JsonProperty("description")
    public String description;
    @JsonProperty("code")
    public int code;

    @Override
    public String toString() {
        return "Status{" +
                "description='" + description + '\'' +
                ", code=" + code +
                '}';
    }
}
