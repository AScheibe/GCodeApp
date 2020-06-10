package SceneControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;


public class MainController extends AbstractController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button editFileButton;

    @FXML
    private Button tutorialButton;

    @FXML
    private Button newFileButton;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     * 
     * Sets menu bar to system default if on MacOS.
     * 
     * Method from interface Initalizeable. Initalizeable interface originally 
     * implemented in "AbstractController."
     * 
     * @param location
     * The location used to resolve relative paths for the root object, or
     * <tt>null</tt> if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or <tt>null</tt> if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuBar.setUseSystemMenuBar(
                System.getProperty("os.name") != null && System.getProperty("os.name").startsWith("Mac"));

    }

    /**
     * Initiates the edit file version of the creator stage
     * 
     * @param event ActionEvent
     * @throws IOException
     */
    @FXML
    void editFilePressed(ActionEvent event) throws IOException {
        Stage stage = (Stage) newFileButton.getScene().getWindow();
        activateEditFileCreatorStage(stage);
    }

    /**
     * Initiates the new file version of the creator stage
     * 
     * @param event ActionEvent
     * @throws IOException
     */
    @FXML
    void newFilePressed(ActionEvent event) throws IOException {
        Stage stage = (Stage) newFileButton.getScene().getWindow();
        activateNewFileCreatorStage(stage);
    }

    //TODO
    @FXML
    void tutorialPressed(ActionEvent event) {

    }
}