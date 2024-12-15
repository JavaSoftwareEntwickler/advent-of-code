package main.java.com.adventofcode.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileProperties {
    public String getPath(String properties){
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/main/resources/file.properties"));
            return prop.getProperty(properties);
        } catch (IOException e) {
            return null;
        }
    }
}
