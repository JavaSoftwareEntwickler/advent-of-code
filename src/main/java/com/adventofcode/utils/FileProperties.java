package main.java.com.adventofcode.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileProperties {
    public String getPath(){
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/main/resources/file.properties"));
            return prop.getProperty("path");
        } catch (IOException e) {
            return null;
        }
    }
}
