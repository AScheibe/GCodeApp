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
            setLines();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public static void setNewMapValue(String newLineText){
        NUM_LINES++;
        TEXT_LINES_MAP.put(NUM_LINES, newLineText);
    }

    private static void setLines() throws FileNotFoundException{
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

    public static void setHeader(String projectTitle, String name, String date){
        HashMap<Integer, String> ogMap = new HashMap<Integer, String>(TEXT_LINES_MAP);

        TEXT_LINES_MAP.put(1, projectTitle);
        TEXT_LINES_MAP.put(2, name);
        TEXT_LINES_MAP.put(3, date);
        TEXT_LINES_MAP.put(4, "(Created with: FREEDOM HS SPARKS)");
        
        System.out.println(TEXT_LINES_MAP.size());

        if(ogMap.size() > 0){
            for(int i = 5; i <= ogMap.size() + 4; i++){
                TEXT_LINES_MAP.put(i, ogMap.get(i - 4));
            }
        }

        NUM_LINES = TEXT_LINES_MAP.size();
    }

    public static void removeLine(int lineNum){
        if(lineNum == TEXT_LINES_MAP.size()){
            TEXT_LINES_MAP.remove(lineNum);
            NUM_LINES--;
        }
        else{
            for(int i = lineNum; i < TEXT_LINES_MAP.size(); i++){
                TEXT_LINES_MAP.remove(i);
                TEXT_LINES_MAP.put(i, TEXT_LINES_MAP.get(i + 1));
            }
            NUM_LINES--;
        }
    }
    
}