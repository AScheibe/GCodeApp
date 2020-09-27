package src.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class TextFileManager {
    private static File textFile;
    private static String textFileShortHand;
    public static int NUM_LINES = 0;
    public static HashMap<Integer, String> TEXT_LINES_MAP = new HashMap<Integer, String>();

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

        try {
            setLineNumbers();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public static void setNewMapValue(String newLineText){
        NUM_LINES++;
        TEXT_LINES_MAP.put(NUM_LINES, newLineText);
    }

    private static void setLineNumbers() throws FileNotFoundException{
        int c = 1;
        Scanner inFile = new Scanner(textFile);

        while(inFile.hasNextLine()){
            String line = inFile.nextLine();
            NUM_LINES = c;
            TEXT_LINES_MAP.put(c, line);
            System.out.println(TEXT_LINES_MAP.get(c));
            c++;
        }
    }
    
}