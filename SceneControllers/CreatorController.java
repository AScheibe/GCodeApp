package SceneControllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;



public abstract class CreatorController extends AbstractController {   
    @FXML
    protected Button button;

    @FXML
    protected MenuBar menuBar;

    @FXML
    protected TextArea textArea;

    protected File textFile;


    //TODO remove this method after testing is complete
    @FXML
    protected void Pressed(final ActionEvent event) throws Exception {
        // final Stage currentStage = (Stage) menuBar.getScene().getWindow();
        List<Void> a = new ArrayList<Void>();
    }

    /**
     * Updates text area to read what the current .txt file that's being edited
     * reads.
     * 
     * The text file is displayed in this way in order to make the text file being
     * edited save immediately with zero doubt that whats displayed in the file
     * is the same as what's being displayed on the screen. The individuals 
     * this application is being made for may be very tecnologically illiterate.
     * @throws FileNotFoundException
     */
    protected void checkFileUpdates() throws FileNotFoundException {
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