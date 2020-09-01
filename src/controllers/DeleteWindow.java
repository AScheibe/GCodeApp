package src.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public class DeleteWindow extends CreatorController{
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuBar.setUseSystemMenuBar(
                System.getProperty("os.name") != null && System.getProperty("os.name").startsWith("Mac"));

        setTextArea();
        setButtonEventHandlers();
        placeButtons();
        addSearchListener();
        addResizeListeners();
        setRecentFilesInMenuBar();

        textArea.setMinWidth(textAreaPane.getWidth());

        HBox.setHgrow(splitPane, Priority.ALWAYS);
        VBox.setVgrow(hBox, Priority.ALWAYS);
    }


    
}