package Util;

import java.io.File;

public class TextFileManager {
    private static File textFile;

    public static File getTextFile()
    {
        return textFile;
    }

    public static void setTextFile(File file)
    {
        textFile = file;
    }
    
}