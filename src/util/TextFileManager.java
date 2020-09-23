package src.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class TextFileManager {
    private static File textFile;
    private static String textFileShortHand;
    private static int numLines = 0;
    private static HashMap<Integer, String> textLinesMap = new HashMap<Integer, String>();

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

    private static void setLineNumbers() throws FileNotFoundException{
        int c = 1;
        Scanner inFile = new Scanner(textFile);

        while(inFile.hasNextLine()){
            String line = inFile.nextLine();
            numLines = c;
            textLinesMap.put(c, line);
            System.out.println(textLinesMap.get(c));
            c++;
        }
    }
    
}