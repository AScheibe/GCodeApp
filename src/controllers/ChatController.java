package src.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import src.chat.ChatRunner;

public class ChatController {
    @FXML
    TextArea textArea;

    @FXML
    TextField textField;
    
    private static ChatRunner chatRunner = new ChatRunner();

    @FXML
    protected void onTextInput(){
        String inputText = textField.getText();

        chatRunner.setInput(inputText);
        textArea.setText(chatRunner.getOutput());
        textField.clear();
    }
}