package com.auk.sdt100.files.demo;

import com.auk.sdt100.files.demo.model.DemoObject;
import com.auk.sdt100.files.demo.utils.SerializationUtils;

import java.io.IOException;
import java.io.NotSerializableException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SerializationFilesDemoRunner {

    // The examples write their files into this folder, inside the project.
    private static final String FOLDER = "demo-output";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Files.createDirectories(Path.of(FOLDER));
        System.out.println("Files are written to: " + Path.of(FOLDER).toAbsolutePath());
        System.out.println();

        // Each example is separate. To run only one, comment out the others.
        singleObjectExample();
        listExample();
        notSerializableExample();
    }

    // Example 1: one object is saved and read back. The copy has the same content, but it is a new object.
    private static void singleObjectExample() throws IOException, ClassNotFoundException {
        System.out.println("Example 1: save one object");
        String file = FOLDER + "/demo-object.ser";
        DemoObject original = new DemoObject(1, "First object");
        SerializationUtils.serialize(original, file);
        DemoObject copy = SerializationUtils.deserialize(file);
        System.out.println("Read back: " + copy);
        System.out.println("Same object? " + (copy == original)); // false: Java created a new object
        System.out.println("Same content? " + copy.equals(original)); // true: equals compares id and name
        System.out.println();
    }

    // Example 2: a list is saved as one object. Each DemoObject inside the list is saved too.
    private static void listExample() throws IOException, ClassNotFoundException {
        System.out.println("Example 2: save a list of objects");
        String file = FOLDER + "/demo-list.ser";
        ArrayList<DemoObject> list = new ArrayList<>();
        list.add(new DemoObject(1, "First object"));
        list.add(new DemoObject(2, "Second object"));
        SerializationUtils.serialize(list, file);
        List<DemoObject> copy = SerializationUtils.deserialize(file);
        for (DemoObject item : copy) {
            System.out.println("Read back: " + item);
        }
        System.out.println();
    }

    // Example 3: only objects whose class implements Serializable can be saved. A plain Object cannot.
    private static void notSerializableExample() throws IOException {
        System.out.println("Example 3: an object that cannot be saved");
        String file = FOLDER + "/not-serializable.ser";
        try {
            SerializationUtils.serialize(new Object(), file);
        } catch (NotSerializableException e) {
            System.out.println("Java says: " + e);
        }
        System.out.println();
    }
}
