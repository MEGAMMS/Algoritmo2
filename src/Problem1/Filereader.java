import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.nio.file.Files;
import java.nio.file.Path;

public class Filereader  {
    public String stringreader(String path){
            // Creating a path choosing file from local
            // directory by creating an object of Path class
            Path fileName
                = Path.of(path);
     
            // Now calling Files.readString() method to
            // read the file
            String str = Files.readString(fileName);
     
            return str;
    }     
    }

