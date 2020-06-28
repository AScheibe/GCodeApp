package SceneControllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import Code.*;

public abstract class AbstractCreatorController extends AbstractController {   
    @FXML
    protected MenuBar menuBar;

    @FXML
    protected TextField searchBar;

    @FXML
    protected TextArea textArea;

    @FXML
    protected Accordion buttonsPane;

    @FXML
    protected TitledPane resultsPane;

    private File textFile;

    private PrintWriter printWriter;


    /**
     * TODO: Sets up the ability for the search bar to detect input and 
     * organizes buttons in alphabetical order. 
     * 
     * 
     * 
     */
    protected void setSearchListener(){
        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {                
                System.out.println("Searching...");
                
                








            }    
        });
    }

    /**
     * Sets the text file that is to be edited.
     * 
     * @param textFile
     */
    protected final void setTextFile(File textFile){
        this.textFile = textFile;
    }

    /**
     * Sets up the text area of the editior scene to display what is currently
     * held in the text file upon start up.
     * 
     */
    protected final void setTextArea()
    {
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
        } catch (FileNotFoundException e) { e.printStackTrace();}
    }
    
    
    /**
     * Writes the action of a code to the file. Called when the GCode and MCode
     * buttons are pressed.
     * 
     * @param code
     * @throws FileNotFoundException
     */
    protected final void writeCode(CodeBasic code) throws FileNotFoundException {
        String text = textArea.getText();
        text += "\n" + code.getAction();
        textArea.setText(text);
    }
    

    /**
     * NOT USED
     * 
     * Updates text area to read what the current .txt file that's being edited
     * reads.
     * 
     * The text file is displayed in this way in order to make the text file being
     * edited save immediately with zero doubt that whats displayed in the file
     * is the same as what's being displayed on the screen. The individuals 
     * this application is being made for may be very tecnologically illiterate.
     * @throws FileNotFoundException
     
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
    */   
}