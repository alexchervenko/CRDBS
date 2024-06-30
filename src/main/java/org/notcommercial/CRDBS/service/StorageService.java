package org.notcommercial.CRDBS.service;

import org.notcommercial.CRDBS.entity.Table;
import org.notcommercial.CRDBS.entity.TableObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

@Service
public class StorageService {
    public void saveDataToFile(String tableName, Map<String, TableObject> content) {
        serializeToFile(tableName, content);
    }

    public Table getDataFromFile(String tableName) {
        Table table = new Table(tableName);
        Object tableData = deserializeFromFile(tableName);
        table.setContent((Map<String, TableObject>) tableData);
        return table;
    }

    public static void serializeToFile(String fileName, Object data) {
        try (FileOutputStream fileOut = new FileOutputStream("src/main/resources/data/" + fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Object deserializeFromFile(String fileName) {
        try (FileInputStream fileIn = new FileInputStream("src/main/resources/data/" + fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
