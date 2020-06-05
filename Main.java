import javafx.application.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import SceneControllers.AbstractController;
import SceneControllers.CreatorController;

public class Main extends Application {

    Scene home;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Main.fxml"));
        Parent root = (Parent)loader.load();
        home = new Scene(root, 640, 400);
        
        primaryStage.setTitle("GCode Creator");
        primaryStage.setScene(home);
        primaryStage.show();
    }

}