package SceneControllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileMadeCreatorController extends AbstractController {


    @FXML
    private Button button;

    @FXML
    private MenuBar menuBar;

    @FXML
    private TextArea textArea;

    private File textFile;
    
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        menuBar.setUseSystemMenuBar(
                System.getProperty("os.name") != null && System.getProperty("os.name").startsWith("Mac"));

        editFileSetUp();
    
        try {
            checkFileUpdates();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void Pressed(final ActionEvent event) throws Exception {
        //final Stage currentStage = (Stage) menuBar.getScene().getWindow();
    }

    /**
     * Opens the file explorer to allow user to select a file
     */

    private void editFileSetUp()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(currentStage);

        textFile = selectedFile;
    }

    /**
     * Updates text area to read what the current .txt file
     * that's being edited reads.
     * 
     * @throws FileNotFoundException
     */
    private void checkFileUpdates() throws FileNotFoundException {
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                while (true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String textToCheck = "";
                            String originalText = "";

                            Scanner fileScanner;

                            try {
                                fileScanner = new Scanner(textFile);
                                while (fileScanner.hasNextLine()) {
                                    textToCheck += fileScanner.nextLine();
                                }
                                fileScanner.close();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            try {
                                fileScanner = new Scanner(textFile);
                                if (!textToCheck.equals(originalText)) {
                                    textArea.setText("");
                                    while (fileScanner.hasNextLine()) {
                                        String currentText = textArea.getText();
                                        String line = fileScanner.nextLine();

                                        textArea.setText(currentText += line + "\n");
                                        originalText += line;
                                    }
                                }

                                fileScanner.close();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Thread.sleep(25);
                }
            }
        };

        Thread threadCheck = new Thread(task);
        threadCheck.setDaemon(true);
        threadCheck.start();
    }

}
