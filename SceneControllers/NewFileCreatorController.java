package SceneControllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class NewFileCreatorController extends AbstractController {

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

        textFile = newFileSetup();

        try {
            checkFileUpdates();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void Pressed(final ActionEvent event) throws Exception {
        // final Stage currentStage = (Stage) menuBar.getScene().getWindow();
    }

    /**
     * Prompts the user to enter a name for the file Opens the file explorer to
     * 
     * Allows user to select a director for the location of the new file
     * 
     * @return newly created file
     */
    private File newFileSetup() {
        while (true) {
            TextInputDialog td = new TextInputDialog();
            td.setContentText("Enter A File Name: ");
            td.showAndWait();

            String fileName = td.getResult();

            File selectedDirectory = new File("foo.txt");
            boolean checkNull = true;
            while (checkNull) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                selectedDirectory = directoryChooser.showDialog(currentStage);
                if (selectedDirectory == null) {
                    // No Directory selected
                } else {
                    checkNull = false;
                    System.out.println(selectedDirectory.getAbsolutePath());
                }
            }

            File file = new File(selectedDirectory.getAbsolutePath() + "/" + fileName + ".txt");

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
        }
    }

    /**
     * Updates text area to read what the current .txt file that's being edited
     * reads.
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
