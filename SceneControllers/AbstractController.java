package SceneControllers;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class for controllers to extend in order to get access to the primary stage
 */
public abstract class AbstractController implements Initializable {
    Stage currentStage;
    boolean newStage;

    /**
     * Opens the welcome stage and closes current stage
     *
     * 
     * @param currentStage current stage of the application
     */
    public void activateWelcomeStage(Stage currentStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/MainPage.fxml"));
        Parent root = (Parent) loader.load();
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
     * Runs program with existing file
     * 
     * @param currentStage current stage of the application
     */
    public void activateExistingCreatorStage(Stage currentStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/FileMadeCreator.fxml"));
        Parent root = (Parent) loader.load();
        Scene maker = new Scene(root, 800, 400);
        Stage creatorStage = new Stage();

        creatorStage.setTitle("GCode Creator");
        creatorStage.setScene(maker);
        creatorStage.show();

        currentStage.close();

        this.currentStage = creatorStage;
        this.newStage = newStage;
    }

    /**
     * Opens the creator stage and closes current stage.
     * 
     * Sets up a new file
     *
     * 
     * @param currentStage current stage of the application
     */
    public void activateNewCreatorStage(Stage currentStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/NewFileCreator.fxml"));
        Parent root = (Parent) loader.load();
        Scene maker = new Scene(root, 800, 400);
        Stage creatorStage = new Stage();

        creatorStage.setTitle("GCode Creator");
        creatorStage.setScene(maker);
        creatorStage.show();

        currentStage.close();

        this.currentStage = creatorStage;
        this.newStage = newStage;
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

}