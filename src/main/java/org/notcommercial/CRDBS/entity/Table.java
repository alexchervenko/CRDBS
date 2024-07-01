package org.notcommercial.CRDBS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public class Table {
    @Getter
    private final String name;

    @Getter
    @Setter
    private HashMap<Integer, TableObject> content = new HashMap<>();

    public void putDataInTable(Integer id, TableObject data) {
        content.putIfAbsent(id, data);
    }

    @JsonIgnore
    public List<TableObject> getListOfTableObjects() {
        return content.values().stream().toList();
    }
}
