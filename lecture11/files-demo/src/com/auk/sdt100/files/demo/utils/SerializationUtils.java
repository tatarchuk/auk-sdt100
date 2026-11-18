package com.auk.sdt100.files.demo.utils;

import java.io.*;

public class SerializationUtils {

    private SerializationUtils() {}

    public static void serialize(Object object, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(object);
        }
    }

    public static <T> T deserialize(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (T) ois.readObject();
        }
    }
}
