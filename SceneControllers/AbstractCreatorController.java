package SceneControllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;

import Code.*;

public abstract class AbstractCreatorController extends AbstractController {   
    @FXML
    protected Button button;

    @FXML
    protected MenuBar menuBar;

    @FXML
    protected TextArea textArea;

    private File textFile;

    private FileWriter fileWriter;
    
    //TODO remove this method after testing is complete
    @FXML
    protected void Pressed(final ActionEvent event) throws Exception {
        // final Stage currentStage = (Stage) menuBar.getScene().getWindow();
        writeCode(GCodes.G1);
    }

    protected final void setTextFile(File textFile){
        this.textFile = textFile;
    }

    protected final void fileWriterInit(){
        try {
            fileWriter = new FileWriter(textFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    protected final void checkFileUpdates() throws FileNotFoundException {
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
    /**
     * Writes the action of a code to the file. Called when
     * the GCode and MCode buttons are pressed. 
     * @param code 
     */
    private final void writeCode(CodeBasic code){
        try {
            fileWriter.write(code.getAction());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }                                                
}