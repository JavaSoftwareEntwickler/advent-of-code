package main.java.com.adventofcode.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    public String getInputString(String pathFile){
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(pathFile)));
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
