package SceneControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

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
    }

    @FXML
    void newFilePressed(ActionEvent event) throws IOException {
        Stage stage = (Stage) newFileButton.getScene().getWindow();
        activateCreatorStage(stage);
    }

    @FXML
    void tutorialPressed(ActionEvent event) {

    }
}