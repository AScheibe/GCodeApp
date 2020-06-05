package SceneControllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CreatorController extends AbstractController{

    private boolean isActive;

    @FXML
    private MenuBar menuBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuBar.setUseSystemMenuBar(
                System.getProperty("os.name") != null && 
                System.getProperty("os.name").startsWith("Mac"));
        isActive = true;

        runCreator();
    }

    @FXML
    void Pressed(ActionEvent event) throws Exception {
        System.out.println("Afds");
        isActive = false;
    }


    private void runCreator(){

    }



}
