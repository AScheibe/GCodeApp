package src.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import src.code.*;

import src.util.TextFileManager;
import src.util.ButtonsUtil;

public class CreatorController extends AbstractController {
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

    @FXML
    protected TitledPane gCodeListPane;

    @FXML
    protected TitledPane mCodeListPane;

    @FXML
    protected HBox hBox;

    @FXML
    protected SplitPane splitPane;

    @FXML
    protected VBox vBoxMain;

    @FXML
    protected VBox vBoxText;

    @FXML
    protected Pane textAreaPane;

    @FXML
    protected Button editWindowButton;

    @FXML
    protected Button deleteWindowButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuBar.setUseSystemMenuBar(
                System.getProperty("os.name") != null && System.getProperty("os.name").startsWith("Mac"));

        setTextArea();
        setButtonEventHandlers();
        placeButtons();
        addSearchListener();
        addResizeListeners();
        setRecentFilesInMenuBar();

        textArea.setMinWidth(textAreaPane.getWidth());

        HBox.setHgrow(splitPane, Priority.ALWAYS);
        VBox.setVgrow(hBox, Priority.ALWAYS);
    }

    public void setLineNumbers(){

    }


    public void addResizeListeners() {
        vBoxMain.widthProperty().addListener((obs, oldVal, newVal) -> {
            textArea.setMinWidth(textAreaPane.getWidth());
        });

        vBoxMain.heightProperty().addListener((obs, oldVal, newVal) -> {
            textArea.setMinHeight(textAreaPane.getHeight());
        });
    }

    @FXML
    public void deleteWindowButtonPressed(ActionEvent event){
        try {
            changeScene(CurrentStage, "/src/Scenes/Delete.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void saveFile(ActionEvent e) throws IOException {
        File file = TextFileManager.getTextFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(file));

        Alert fileSavedAlert = new Alert(AlertType.INFORMATION);
        fileSavedAlert.setTitle("FILE SAVED");
        fileSavedAlert.setHeaderText(TextFileManager.getTextFileShortHand() + " saved");
        fileSavedAlert.setHeight(5);
        fileSavedAlert.show();

        out.write(textArea.getText());
        out.close();
    }

    //TODO
    public void saveFileAs(ActionEvent e){}
    
    /**
     * Adds a search listener that detects change in text in the search bar. The
     * listener in turn displays buttons ordered in alphabetical order.
     * 
     */
    protected void addSearchListener() {
        searchBar.textProperty().addListener((ChangeListener<String>) (ov, t, t1) -> {
            if (!(searchBar.getText().equals(" ") || searchBar.getText().equals("")
                    || searchBar.getText().substring(0).contains("  ")
                            && searchBar.getText().substring(0, 1).equals(" "))) {

                System.out.println("Searching...");

                buttonsPane.setVisible(false);
                resultsPane.setVisible(true);

                GridPane grid = new GridPane();

                for (int i = 0; i < ButtonsUtil.MASTER_LIST.size(); i++) {
                    if (ButtonsUtil.MASTER_LIST.get(i).getText().toLowerCase()
                            .contains(searchBar.getText().toLowerCase())) {
                        grid.add(ButtonsUtil.MASTER_LIST.get(i), 0, i + 5);
                    }
                }

                resultsPane.setContent(grid);
            }

            else {
                placeButtons();
                resultsPane.setVisible(false);
                buttonsPane.setVisible(true);
            }
        });
    }

    /**
     * Places buttons used by the application in their respective dropdown menus. To
     * be called after a search is made.
     * 
     */
    protected void placeButtons() {
        GridPane gCodeButtonGrid = new GridPane();
        GridPane mCodeButtonGrid = new GridPane();

        int count = 0;
        for (Button b : ButtonsUtil.GCODE_LIST) {
            gCodeButtonGrid.add(b, 0, count);
            count++;
        }

        count = 0;
        for (Button b : ButtonsUtil.MCODE_LIST) {
            mCodeButtonGrid.add(b, 0, count);
            count++;
        }

        gCodeListPane.setContent(gCodeButtonGrid);
        mCodeListPane.setContent(mCodeButtonGrid);
    }

    /**
     * Sets up the text area of the editior scene to display what is currently held
     * in the text file upon start up.
     * 
     */
    protected void setTextArea() {
        String textToCheck = "";
        String originalText = "";

        Scanner fileScanner;

        File textFile = TextFileManager.getTextFile();

        try {
            fileScanner = new Scanner(textFile);

            while (fileScanner.hasNextLine()) {
                textToCheck += fileScanner.nextLine();
            }
            fileScanner.close();
        } catch (Exception e) {
            try {
                activateMainStage();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        try {
            fileScanner = new Scanner(textFile);
            if (!textToCheck.equals(originalText)) {
                textArea.setText("");
                while (fileScanner.hasNextLine()) {
                    String currentText = textArea.getText();
                    String line = fileScanner.nextLine();

                    if(fileScanner.hasNextLine()){
                        textArea.setText(currentText += line + "\n");
                    }
                    else{
                        textArea.setText(currentText += line);
                    }
                    originalText += line;   
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the actions of each button to be used when writing out the GCode file.
     * 
     */
    public void setButtonEventHandlers() {
        for (Button b : ButtonsUtil.MASTER_LIST) {
            String buttonName = b.getText();
            CodeBasic code = parseButtonName(buttonName);

            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    try {
                        writeCode(code);
                    } catch (FileNotFoundException e1) {
                        System.out.println("ERROR: FILE NOT FOUND");
                    }
                }
            });
        }
    }

    /**
     * Based on the name of button passed through, method returns an appropriate
     * code object in order to properly later write a line of code in the GCode
     * file.
     * 
     * @return CodeBasic
     */
    protected CodeBasic parseButtonName(String buttonName) {
        for (CodeBasic c : GCodes.values()) {
            if (buttonName.equals(c.getName()))
                return c;
        }

        for (CodeBasic c : MCodes.values()) {
            if (buttonName.equals(c.getName()))
                return c;
        }

        return null;
    }

    /**
     * Writes the action of a code to the file. Called when the GCode and MCode
     * buttons are pressed.
     * 
     * @param code
     * @throws FileNotFoundException
     */
    protected void writeCode(CodeBasic code) throws FileNotFoundException {
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
     * edited save immediately w ith zero doubt that whats displayed in the file is
     * the same as what's being displayed on the screen. The individuals this
     * application is being made for may be very tecnologically illiterate.
     * 
     * @throws FileNotFoundException
     * 
     *                               protected final void checkFileUpdates() throws
     *                               FileNotFoundException { Task<Void> task = new
     *                               Task<Void>() {
     * @Override public Void call() throws Exception { while (true) {
     *           Platform.runLater(new Runnable() {
     * @Override public void run() { String textToCheck = ""; String originalText =
     *           "";
     * 
     *           Scanner fileScanner;
     * 
     *           try { fileScanner = new Scanner(textFile); while
     *           (fileScanner.hasNextLine()) { textToCheck +=
     *           fileScanner.nextLine(); } fileScanner.close(); } catch
     *           (FileNotFoundException e) { e.printStackTrace(); }
     * 
     *           try { fileScanner = new Scanner(textFile); if
     *           (!textToCheck.equals(originalText)) { textArea.setText(""); while
     *           (fileScanner.hasNextLine()) { String currentText =
     *           textArea.getText(); String line = fileScanner.nextLine();
     * 
     *           textArea.setText(currentText += line + "\n"); originalText += line;
     *           } }
     * 
     *           fileScanner.close(); } catch (FileNotFoundException e) {
     *           e.printStackTrace(); } } }); Thread.sleep(25); } } };
     * 
     *           Thread threadCheck = new Thread(task); threadCheck.setDaemon(true);
     *           threadCheck.start(); }
     */
}