package SceneControllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class for controllers to extend in order to get access to the primary stage
 */
public abstract class AbstractController {
    protected Stage primaryStage;

    /**
     * Sets the primary stage.
     *
     * 
     * @param primaryStage the primary stage for the application
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void activateCreatorStage(Stage currentStage, String fxmlScenePath) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Test.fxml"));
        Parent root = (Parent)loader.load();
        Scene maker = new Scene(root, 800, 400);
        Stage creatorStage = new Stage();
        
        creatorStage.setTitle("GCode Creator");
        creatorStage.setScene(maker);
        creatorStage.show();

        currentStage.close();
    }

    public void changeScene(Stage stage, String fxml) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = (Parent)loader.load();

        Scene scene = new Scene(root, 640, 400);
        stage.setScene(scene);
    }
}