package src.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import src.chat.ChatRunner;

public class ChatController {
    @FXML
    TextArea textArea;

    @FXML
    TextField textField;
    
    private ChatRunner chatRunner;

    public void initialize(URL location, ResourceBundle resources) {
        chatRunner = new ChatRunner();
    }

    @FXML
    protected void onTextInput(){
        chatRunner.setInput(textField.getText());
        textArea.setText(chatRunner.getOutput());
    }
}