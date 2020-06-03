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
     * Opens the welcome stage and closes current stage
     *
     * 
     * @param currentStage current stage of the application
     */
    public void activateWelcomeStage(Stage currentStage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/MainPage.fxml"));
        Parent root = (Parent)loader.load();
        Scene maker = new Scene(root, 640, 400);
        Stage primaryStage = new Stage();
        
        primaryStage.setTitle("GCode Creator");
        primaryStage.setScene(maker);
        primaryStage.show();

        currentStage.close();
    }

    /**
     * Opens the creator stage and closes current stage
     *
     * 
     * @param currentStage current stage of the application
     */
    public void activateCreatorStage(Stage currentStage) throws IOException
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