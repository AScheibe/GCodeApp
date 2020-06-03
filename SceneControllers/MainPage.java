package SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainPage extends AbstractController{

    @FXML
    private Button MainButton;

    @FXML
    void Pressed(ActionEvent event) throws Exception {
        Stage stage = (Stage)MainButton.getScene().getWindow();

        activateCreatorStage(stage);
    }

}