package src.chat;

import java.util.ArrayList;

import src.chat.words.ObjectWords;
import src.chat.words.QuestionWords;
import src.chat.words.Responses;

public class ChatRunner {
    private String inputText;
    private String outputText;
    private ArrayList<String> notableWords;

    public ChatRunner() {
        inputText = "";
    }

    public void setInput(String inputText) {
        this.inputText = inputText.toLowerCase();
        figureOutput();
    }

    // TODO
    public String getOutput() {
        return outputText;
    }

    private void figureOutput() {
        boolean active = true;

        if (inputText.contains("bye")) {
            outputText = "Have a good day!";
            active = false;
        }

        while (active) {
            notableWords = Parser.parse(inputText);

            if (notableWords.size() == 0) {
                outputText = "I didn't quite get that. Please try again. Type \"help\" if you need assistance.";
                active = false;
            }

            if (notableWords.contains(QuestionWords.how.getWord())) {
                if (notableWords.contains(ObjectWords.circle.getWord())) {
                    outputText = Responses.makeCircle.getResponse();
                    active = false;
                }
            }

            else {
                outputText = "I didn't quite get that. Please try again. Type \"help\" if you need assistance.";
                active = false;
            }

        }
    }

}
