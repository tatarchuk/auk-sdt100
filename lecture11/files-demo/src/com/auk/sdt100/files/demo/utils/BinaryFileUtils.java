package com.auk.sdt100.files.demo.utils;

import java.io.*;

public class BinaryFileUtils {

    private BinaryFileUtils() {}

    public static void insertOrReplaceStringInBinaryFile(
            String fileName,
            String content)
            throws IOException {
        writeStringToBinaryFile(fileName, content, false);
    }

    public static void appendStringInBinaryFile(
            String fileName,
            String content)
            throws IOException {
        writeStringToBinaryFile(fileName, content, true);
    }

    private static void writeStringToBinaryFile(
            String fileName,
            String content,
            boolean append)
            throws IOException {
        DataOutputStream outputFile =
                new DataOutputStream(
                        new FileOutputStream(fileName, append));
        outputFile.writeUTF(content);
        outputFile.close();
    }

    // What's wrong with this code? Why try-with-resources better fits here?
    public static String readBinaryFile(String fileName) throws IOException {
        FileInputStream fstream = new FileInputStream(fileName);
        DataInputStream inputFile = new DataInputStream(fstream);
        String content = inputFile.readUTF();
        inputFile.close();
        return content;
    }

}
