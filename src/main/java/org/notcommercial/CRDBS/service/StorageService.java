package org.notcommercial.CRDBS.service;

import org.notcommercial.CRDBS.entity.Table;
import org.notcommercial.CRDBS.entity.TableObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class StorageService {
    public synchronized void saveDataToFile(String tableName, Map<Integer, TableObject> content) {
        serializeToFile(tableName, content);
    }

    public synchronized Table getDataFromFile(String tableName) {
        Table table = new Table(tableName);
        Object tableData = deserializeFromFile(tableName);
        table.setContent((HashMap<Integer, TableObject>) tableData);
        return table;
    }

    public static void serializeToFile(String fileName, Object data) {
        try (FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + "\\" + fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Object deserializeFromFile(String fileName) {
        try (FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + "\\" + fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
