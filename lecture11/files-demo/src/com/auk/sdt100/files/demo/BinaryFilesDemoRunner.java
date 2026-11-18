package com.auk.sdt100.files.demo;

import com.auk.sdt100.files.demo.utils.BinaryFileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BinaryFilesDemoRunner {

    // The examples write their files into this folder, inside the project.
    private static final String FOLDER = "demo-output";

    public static void main(String[] args) throws IOException {
        Files.createDirectories(Path.of(FOLDER));
        System.out.println("Files are written to: " + Path.of(FOLDER).toAbsolutePath());
        System.out.println();

        // Each example is separate. To run only one, comment out the others.
        insertOrReplaceExample();
        appendExample();
    }

    // Example 1: insertOrReplaceStringInBinaryFile replaces the whole file with the new text.
    private static void insertOrReplaceExample() throws IOException {
        System.out.println("Example 1: insertOrReplaceStringInBinaryFile (replaces the file)");
        String file = FOLDER + "/binary-replace.dat";
        BinaryFileUtils.insertOrReplaceStringInBinaryFile(file, "Hello Binary World!");
        BinaryFileUtils.insertOrReplaceStringInBinaryFile(file, "New text"); // replaces the first text
        // 8 characters of text plus a 2-byte length written before them: 10 bytes in total.
        System.out.println("File size: " + Files.size(Path.of(file)) + " bytes");
        System.out.println("Read back: " + BinaryFileUtils.readBinaryFile(file));
        System.out.println();
    }

    // Example 2: appendStringInBinaryFile adds the text to the end of the file.
    private static void appendExample() throws IOException {
        System.out.println("Example 2: appendStringInBinaryFile (adds to the end)");
        String file = FOLDER + "/binary-append.dat";
        BinaryFileUtils.insertOrReplaceStringInBinaryFile(file, "First");
        BinaryFileUtils.appendStringInBinaryFile(file, "Second");
        // Two texts are in the file, and each one has its own 2-byte length: 15 bytes in total.
        System.out.println("File size: " + Files.size(Path.of(file)) + " bytes");
        // readUTF reads one text and stops, so only "First" comes back, even though "Second" is in the file.
        System.out.println("Read back: " + BinaryFileUtils.readBinaryFile(file));
        System.out.println();
    }
}
