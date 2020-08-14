package src.util;

import java.io.File;

public class TextFileManager {
    private static File textFile;
    private static String textFileShortHand;

    public static File getTextFile()
    {
        return textFile;
    }

    public static String getTextFileShortHand()
    {
        return textFileShortHand;
    }


    public static void setTextFile(File file)
    {
        textFile = file;
        textFileShortHand = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("/") + 1);
    }
    
}