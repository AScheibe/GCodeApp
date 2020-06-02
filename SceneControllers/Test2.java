package SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Test2 extends AbstractController{

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
        System.out.println("success");

    }
    
}
