package com.auk.sdt100.files.demo.utils;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileUtils {

    private RandomAccessFileUtils() {}

    public static void runReadOnlyExample(String filePath, int position) throws IOException {
        try (RandomAccessFile rFile = new RandomAccessFile(filePath, "r")) {
            // Jump directly to byte index
            rFile.seek(position);

            // Read the rest of the line from the current pointer
            String result = rFile.readLine();
            System.out.println("Read from index " + position + ": " + result); // Output: Disk World!
        }
    }

    public static void runReadWriteExample(String filePath, int position) throws IOException {
        try (RandomAccessFile rwFile = new RandomAccessFile(filePath, "rw")) {
            // Write initial text at byte 0
            rwFile.writeBytes("Hello Java World!");

            // Move the file pointer to byte index
            rwFile.seek(position);

            rwFile.writeBytes("Disk");

            System.out.println("File size: " + rwFile.length() + " bytes");
        }
    }

}
