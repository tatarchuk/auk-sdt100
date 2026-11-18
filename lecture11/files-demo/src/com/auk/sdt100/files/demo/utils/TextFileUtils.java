package com.auk.sdt100.files.demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class TextFileUtils {

    private TextFileUtils() {}

    public static void callPrintWriterDemoExample(String fileName, int[] numberArrayToWrite) throws IOException {
        try (PrintWriter outputFile = new PrintWriter(fileName)) {
            outputFile.println("Hello, World!");
            outputFile.println("This is a test.");
            for (int number : numberArrayToWrite) {
                outputFile.println(number);
            }
        }
    }

    public static void callFileWriterDemoExample(String fileName, int[] numberArrayToWrite) throws IOException {
        // FileWriter fw = new FileWriter(fileName);
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, true))) {
            pw.println("Hello, AUK students!");
            pw.println("This is a customised test.");
            for (int number : numberArrayToWrite) {
                pw.println(number);
            }
        }
    }

    // java.nio example for path resolution
    public static void callFileWriterWithPathDetectionDemoExample(String pathToFolder, String fileName) throws IOException {
        // resolve() joins the folder and the file name with the separator of the current OS:
        // data/students.txt on macOS and Linux, data\students.txt on Windows.
        Path folderPath = Path.of(pathToFolder);
        Path filePath = folderPath.resolve(fileName);

        System.out.println("OS:            " + System.getProperty("os.name"));
        System.out.println("Separator:     " + File.separator);
        System.out.println("File path:     " + filePath);
        System.out.println("Absolute path: " + filePath.toAbsolutePath());
        System.out.println("Is absolute:   " + filePath.isAbsolute());
        System.out.println("Folder exists: " + Files.isDirectory(folderPath));
        System.out.println("File exists:   " + Files.exists(filePath));

        // FileWriter cannot create missing folders, so create them first.
        Files.createDirectories(folderPath);

        // FileWriter takes a File, so convert the Path with toFile().
        PrintWriter pw = new PrintWriter(new FileWriter(filePath.toFile(), true));
        pw.println("Hello, AUK students!");
        pw.close();
    }

    public static void readTextFileOldFashionedWay(String fileName, final int arrayLength) throws IOException {
        int[] numbers = new int[arrayLength];
        int i = 0;
        try (Scanner inputFile = new Scanner(new File(fileName))) {
            while (inputFile.hasNext() && i < arrayLength) {
                numbers[i] = inputFile.nextInt();
                i++;
            }
        }
        System.out.println(Arrays.toString(numbers));
    }

    public static void readTextFileUsingJavaNio(String fileName) throws IOException {
        Path filePath = Path.of(fileName);
        String content = new String(Files.readAllBytes(filePath));
        System.out.println(content);
    }

    public static void readLargeTextFileUsingJavaNio(String fileName) throws IOException {
        Path filePath = Path.of(fileName);
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
