package org.notcommercial.CRDBS.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class TableObject implements Serializable {
    @Getter
    private final String id;

    @Getter
    @Setter
    @JsonProperty("object_data")
    private Map<String, String> objectData = new HashMap<>();

    private final UUID uuid = UUID.randomUUID();

    public TableObject() {
        this.id = uuid.toString();
        objectData.put("id", id);
    }
}
