package com.auk.sdt100.files.demo;

import com.auk.sdt100.files.demo.utils.TextFileUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFilesDemoRunner {

    // The examples write their files into this folder, inside the project.
    private static final String FOLDER = "demo-output";

    public static void main(String[] args) throws IOException {
        // PrintWriter and FileWriter cannot create folders, so create this one first.
        Files.createDirectories(Path.of(FOLDER));
        System.out.println("Files are written to: " + Path.of(FOLDER).toAbsolutePath());
        System.out.println();

        // Each example is separate. To run only one, comment out the others.
        printWriterExample();
        fileWriterAppendExample();
        filePathExample();
        scannerExample();
        readAllBytesExample();
        bufferedReaderExample();
        missingFileExample();
    }

    // Example 1: PrintWriter creates the file. If the file already exists, its content is replaced.
    private static void printWriterExample() throws IOException {
        System.out.println("Example 1: PrintWriter (replaces the file)");
        String file = FOLDER + "/numbers.txt";
        TextFileUtils.callPrintWriterDemoExample(file, new int[] {1, 2, 3});
        TextFileUtils.readTextFileUsingJavaNio(file);
        System.out.println();
    }

    // Example 2: FileWriter with true as the second argument adds to the end of the file.
    private static void fileWriterAppendExample() throws IOException {
        System.out.println("Example 2: FileWriter with append (adds to the end)");
        String file = FOLDER + "/log.txt";
        Files.deleteIfExists(Path.of(file)); // start without the file, so every run looks the same
        TextFileUtils.callFileWriterDemoExample(file, new int[] {1, 2});
        TextFileUtils.callFileWriterDemoExample(file, new int[] {3}); // this call adds more lines
        TextFileUtils.readTextFileUsingJavaNio(file);
        System.out.println();
    }

    // Example 3: Path.of() and resolve() use the separator of your OS: '/' on macOS, '\' on Windows.
    private static void filePathExample() throws IOException {
        System.out.println("Example 3: file paths on your OS");
        String folder = FOLDER + "/lessons/week11";
        Files.deleteIfExists(Path.of(folder, "students.txt"));
        TextFileUtils.callFileWriterWithPathDetectionDemoExample(folder, "students.txt");
        System.out.println();
    }

    // Example 4: Scanner reads numbers. This file has only numbers, because Scanner stops at text.
    private static void scannerExample() throws IOException {
        System.out.println("Example 4: Scanner reads numbers");
        String file = FOLDER + "/scores.txt";
        try (PrintWriter out = new PrintWriter(file)) {
            out.println(10);
            out.println(20);
            out.println(30);
        }
        TextFileUtils.readTextFileOldFashionedWay(file, 3); // prints [10, 20, 30]
        System.out.println();
    }

    // Example 5: Files.readAllBytes reads the whole file at once. Fine for small files.
    private static void readAllBytesExample() throws IOException {
        System.out.println("Example 5: Files.readAllBytes (whole file at once)");
        String file = FOLDER + "/numbers.txt";
        TextFileUtils.callPrintWriterDemoExample(file, new int[] {1, 2, 3});
        TextFileUtils.readTextFileUsingJavaNio(file);
        System.out.println();
    }

    // Example 6: BufferedReader reads one line at a time, so it also works for very big files.
    private static void bufferedReaderExample() throws IOException {
        System.out.println("Example 6: BufferedReader (one line at a time)");
        String file = FOLDER + "/numbers.txt";
        TextFileUtils.callPrintWriterDemoExample(file, new int[] {1, 2, 3});
        TextFileUtils.readLargeTextFileUsingJavaNio(file);
        System.out.println();
    }

    // Example 7: a file that does not exist. Java throws an exception, and the catch block handles it.
    private static void missingFileExample() {
        System.out.println("Example 7: a file that does not exist");
        try {
            TextFileUtils.readTextFileUsingJavaNio(FOLDER + "/does-not-exist.txt");
        } catch (IOException e) {
            System.out.println("Java says: " + e);
        }
        System.out.println();
    }
}
