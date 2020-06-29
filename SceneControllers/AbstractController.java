package SceneControllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

/**
 * Class for controllers to extend in order to get access to the primary stage
 */
public abstract class AbstractController implements Initializable {


    @FXML
    protected MenuBar menuBar;
    
    protected Stage currentStage;
    
    /**
     * Opens the welcome stage and closes current stage
     * 
     * @param currentStage current stage of the application
     */
    public void activateMainStage(Stage currentStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Main.fxml"));
        Parent root = (Parent) loader.load();
        Scene maker = new Scene(root, 640, 400);
        Stage primaryStage = new Stage();

        primaryStage.setTitle("GCode Creator");
        primaryStage.setScene(maker);
        primaryStage.show();

        currentStage = primaryStage;
    }

    /**
     * Opens the creator stage and closes current stage.
     * 
     * Sets up a new file
     *
     * 
     * @param currentStage current stage of the application
     */
    public void activateCreatorController(Stage currentStage) throws IOException {
        currentStage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/FileCreator.fxml"));
        Parent root = (Parent) loader.load();
        Scene maker = new Scene(root, 800, 550);
        Stage creatorStage = new Stage();

        creatorStage.setTitle("GCode Creator");
        creatorStage.setScene(maker);
        creatorStage.show();

        currentStage = creatorStage;
    }

    /**
     * Changes the scene to the specefied fxml file
     *
     * 
     * @param currentStage current stage of the application
     * @param fxml         file path for fxml file to pull from for scene design
     */
    public void changeScene(Stage currentStage, String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root, 640, 400);

        currentStage.setScene(scene);
    }

    //TODO set up sylesheet selection
    public void setStyleSheet(Stage currentStage)
    {
    }

    //TODO set up preferences reading
    public void readPreferences()
    {
    }
}