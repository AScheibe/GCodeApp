package SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Test extends AbstractController{

    @FXML
    private Button button;

    @FXML
    private VBox Test;

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    void Pressed(ActionEvent event) throws Exception {
        Stage currentStage = (Stage) button.getScene().getWindow();
        activateWelcomeStage(currentStage);
    }

    
}
