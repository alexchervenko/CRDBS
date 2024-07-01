package org.notcommercial.CRDBS.controller;

import org.notcommercial.CRDBS.entity.Table;
import org.notcommercial.CRDBS.entity.TableObject;
import org.notcommercial.CRDBS.service.StorageService;
import org.notcommercial.CRDBS.service.TableObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/api")
public class TableController {
    private final TableObjectService tableObjectService;
    private final StorageService storageService;

    @Autowired
    public TableController(TableObjectService tableObjectService,
                           StorageService storageService) {
        this.tableObjectService = tableObjectService;
        this.storageService = storageService;
    }

    @GetMapping("/get/{tableName}")
    public ResponseEntity<Table> getTable(@PathVariable String tableName) {
        Table table = storageService.getDataFromFile(tableName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(table);
    }

    @GetMapping("/get/{tableName}/list")
    public ResponseEntity<List<TableObject>> getListFromTable(@PathVariable String tableName) {
        Table table = storageService.getDataFromFile(tableName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(table.getListOfTableObjects());
    }

    @GetMapping("/get/{tableName}/{id}")
    public ResponseEntity<TableObject> getObjectFromTable(@PathVariable String tableName,
                                                          @PathVariable Integer id) {
        Table table = storageService.getDataFromFile(tableName);
        return ResponseEntity.status(HttpStatus.OK).body(table.getContent().get(id));
    }

    @PostMapping("/create-table/{name}")
    public ResponseEntity<Table> createTable(@PathVariable String name) {
        Table table = new Table(name);
        storageService.saveDataToFile(table.getName(), table.getContent());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(table);
    }

    @PostMapping("/{tableName}/create-object")
    public ResponseEntity<TableObject> createTableObject(@PathVariable String tableName,
                                                         @RequestBody TableObject tableObject) {
        Table table = storageService.getDataFromFile(tableName);
        table.putDataInTable(tableObject.getId(), tableObject);
        storageService.saveDataToFile(table.getName(), table.getContent());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tableObject);

    }

}
