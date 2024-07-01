package org.notcommercial.CRDBS.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;


public class TableObject implements Serializable {
    @Getter
    private final int id;

    private static int instanceCounter = 1;

    @Getter
    @Setter
    @JsonProperty("object_data")
    private HashMap<Object, Object> objectData = new HashMap<>();

    public TableObject() {
        this.id = instanceCounter;
        objectData.put("id", id);
        instanceCounter++;
    }
}
