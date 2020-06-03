package SceneControllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class MainPage extends AbstractController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button MainButton;

    @FXML
    public void initalize() {
        menuBar.setUseSystemMenuBar(true);
        System.out.print("success");
    }

    @FXML
    void Pressed(ActionEvent event) throws Exception {
        Stage stage = (Stage) MainButton.getScene().getWindow();
        activateCreatorStage(stage);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuBar.setUseSystemMenuBar(
                System.getProperty("os.name") != null && 
                System.getProperty("os.name").startsWith("Mac")
        );
    }

}