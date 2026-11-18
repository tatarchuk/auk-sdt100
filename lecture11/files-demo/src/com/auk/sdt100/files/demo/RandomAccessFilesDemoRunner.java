package com.auk.sdt100.files.demo;

import com.auk.sdt100.files.demo.utils.RandomAccessFileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RandomAccessFilesDemoRunner {

    // The examples write their files into this folder, inside the project.
    private static final String FOLDER = "demo-output";

    public static void main(String[] args) throws IOException {
        Files.createDirectories(Path.of(FOLDER));
        String file = FOLDER + "/random-access.txt";
        Files.deleteIfExists(Path.of(file)); // start without the file, so every run looks the same

        // The read-only example needs content in the file, so the read-write example writes it first.
        RandomAccessFileUtils.runReadWriteExample(file, 6); // the file now holds "Hello Disk World!"
        RandomAccessFileUtils.runReadOnlyExample(file, 6);  // prints "Read from index 6: Disk World!"
    }
}
