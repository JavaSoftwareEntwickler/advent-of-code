package main.java.com.adventofcode.utils;

public class AdventOfCode {
    FileProperties fileProperties;
    FileUtils fileUtils;
    private String path;

    public AdventOfCode(String nomeFile){
        fileUtils = new FileUtils();
        fileProperties = new FileProperties();
        this.path = fileProperties.getPath("path") + "/" + nomeFile;
    }

    public String getPath(){
        return this.path;
    }
    public String getInputString(){
       return fileUtils.getInputString(this.path);
    }
}
