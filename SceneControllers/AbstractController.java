package SceneControllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

import Util.RecentFilesUtil;
import Util.TextFileManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Class for controllers to extend in order to get access to the primary stage
 */
public abstract class AbstractController implements Initializable {

    @FXML
    protected MenuBar menuBar;

    @FXML
    protected Menu openRecentMenu;

    public static Stage CurrentStage;

    /**
     * Opens the welcome stage and closes current stage
     * 
     */
    public void activateMainStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Main.fxml"));
        Parent root = (Parent) loader.load();
        Scene maker = new Scene(root, 647, 450);
        Stage primaryStage = new Stage();

        primaryStage.setTitle("GCode Creator");
        primaryStage.setScene(maker);
        primaryStage.show();
        CurrentStage = primaryStage;
    }

    /**
     * Opens the creator stage and closes current stage.
     * 
     * Sets up a new file
     *
     * 
     */
    public void activateCreatorController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/FileCreator.fxml"));
        Parent root = (Parent) loader.load();
        Scene maker = new Scene(root, 800, 760);
        Stage creatorStage = new Stage();

        creatorStage.setTitle("GCode Creator");
        creatorStage.setScene(maker);

        creatorStage.show();

        CurrentStage.close();
        CurrentStage = creatorStage;
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

    public void manageMenuBar() {

    }

    protected void setRecentFilesInMenuBar() {
        List<String> rFList = RecentFilesUtil.recentFilesList;

        for (int i = 0; i < rFList.size(); i++) {
            String fileShort = rFList.get(i).substring(rFList.get(i).lastIndexOf("/") + 1);
            MenuItem recentItem = new MenuItem(fileShort);
            int index = i;

            recentItem.setOnAction(event -> {
                File file = new File(rFList.get(index));
                TextFileManager.setTextFile(file);
                try {
                    activateCreatorController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            openRecentMenu.getItems().add(recentItem);
        }
    }

    // TODO set up sylesheet selection
    public void setStyleSheet(Stage currentStage) {
    }

    // TODO set up preferences reading
    public void readPreferences() {

    }
}