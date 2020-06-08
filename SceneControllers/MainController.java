package SceneControllers;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController extends AbstractController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button editFileButton;

    @FXML
    private Button tutorialButton;

    @FXML
    private Button newFileButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuBar.setUseSystemMenuBar(
                System.getProperty("os.name") != null && System.getProperty("os.name").startsWith("Mac"));

    }

    @FXML
    void editFilePressed(ActionEvent event) {
        Stage stage = (Stage) newFileButton.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(stage);

        setTextFile(selectedFile);

        try {
            activateCreatorStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void newFilePressed(ActionEvent event) {
        Stage stage = (Stage) newFileButton.getScene().getWindow();
        try {
            activateCreatorStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void tutorialPressed(ActionEvent event) {

    }
}