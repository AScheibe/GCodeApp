package SceneControllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Stage;

public class PrimaryStageDataTransfer extends AbstractController {
    public static void transfer(Stage stage) {
        currentStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}