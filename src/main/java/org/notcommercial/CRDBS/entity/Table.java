package org.notcommercial.CRDBS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class Table {
    @Getter
    private final String name;

    @Getter
    @Setter
    private Map<String, Map> content = new HashMap<>();

    public void putDataInTable(String id, Map data){
        content.putIfAbsent(id, data);
    }

    @JsonIgnore
    public List<Map> getListOfTableObjects() {
        return content.values().stream().toList();
    }
}
