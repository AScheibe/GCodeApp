package SceneControllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * 
 * 
 * Availabale methods in CreatorController: fileWriterInit(), checkFileUpdates().
 */
public class EditFileCreatorController extends AbstractCreatorController {

    private FileWriter fileWriter;

    /**
     * Called to initialize a controller after its root element has been completely
     * processed.
     * 
     * Sets menu bar to system default if on MacOS.
     * 
     * Opens a file into the application to be edited.
     * 
     * Checks for updates to the text file.
     * 
     * Method from interface Initalizeable. Initalizeable interface originally
     * implemented in "AbstractController."
     * 
     *
     * @param location  The location used to resolve relative paths for the root
     *                  object, or <tt>null</tt> if the location is not known.
     *
     * @param resources The resources used to localize the root object, or
     *                  <tt>null</tt> if the root object was not localized.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        menuBar.setUseSystemMenuBar(
                System.getProperty("os.name") != null && System.getProperty("os.name").startsWith("Mac"));

        File textFile = editFileSetUp();
        setTextFile(textFile);

        try {
            fileWriterInit();
            checkFileUpdates();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
