package src.chat;

import java.util.ArrayList;

public class ChatRunner {
    private String inputText;
    private String outputText;
    private ArrayList<String> validWords;

    public ChatRunner(){
        inputText = "";
    }

    //TODO
    public void setInput(String inputText){
        this.inputText = inputText; 
        figureOutput();
    }

    //TODO
    public String getOutput(){
        return null;
    }

    private void figureOutput(){
        validWords = Parser.parse(inputText);
    }


}
