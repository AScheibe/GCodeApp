package SceneControllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Util.TextFileManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController extends AbstractController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button editFileButton;

    @FXML
    private Button tutorialButton;

    @FXML
    private Button newFileButton;

    private File file;

    /**
     * Called to initialize a controller after its root element has been completely
     * processed.
     * 
     * Sets menu bar to system default if on MacOS.
     * 
     * Method from interface Initalizeable. Initalizeable interface originally
     * implemented in "AbstractController."
     * 
     * @param location  The location used to resolve relative paths for the root
     *                  object, or <tt>null</tt> if the location is not known.
     *
     * @param resources The resources used to localize the root object, or
     *                  <tt>null</tt> if the root object was not localized.
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

        file = editFileSetUp();

        TextFileManager.setTextFile(file);
        activateCreatorController(stage);
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

        file = newFileSetup();
        TextFileManager.setTextFile(file);
        activateCreatorController(stage);
    }

    // TODO
    @FXML
    void tutorialPressed(ActionEvent event) {

    }

    /**
     * Creates and places a new file through user input and the selection of a
     * directory in the file explorer.
     * 
     * @return File textFile
     */
    private File newFileSetup() {
        while (true) {

            TextInputDialog td = new TextInputDialog();

            td.setContentText("Enter A File Name: ");
            td.showAndWait();

            String fileName = td.getResult();

            if (fileName != null) {
                File selectedDirectory = new File("foo.txt"); // must be initalized in order to avoid errors in code
                                                              // below
                boolean checkNull = true;

                while (checkNull) {
                    DirectoryChooser directoryChooser = new DirectoryChooser();
                    selectedDirectory = directoryChooser.showDialog(currentStage);
                    if (selectedDirectory == null) {
                        return null;
                    } else {
                        checkNull = false;
                        System.out.println(selectedDirectory.getAbsolutePath());
                    }
                }

                File file = new File(selectedDirectory.getAbsolutePath() + "/" + fileName + ".txt");

                // Error management
                if (!file.exists() && !file.getAbsolutePath().contains("null")) {
                    try {
                        file.createNewFile();
                        System.out.println(file.getAbsolutePath());
                        return file;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (file.getAbsolutePath().contains("null")) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR MESSAGE");
                    alert.setContentText("ERROR: File Name OR Directory Not Specified");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR MESSAGE");
                    alert.setContentText("ERROR: File Already Created");
                    alert.showAndWait();

                }
            } else {
                break;
            }
        }
        return null;
    }

    /**
     * Opens the file explorer to allow user to select a file
     */
    private File editFileSetUp() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(currentStage);

        return selectedFile;
    }
}