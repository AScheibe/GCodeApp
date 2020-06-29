package SceneControllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import Code.*;
import Util.TextFileManager;

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
    protected Button one;

    @FXML
    protected Button two;

    @FXML
    protected Button three;

    @FXML
    protected Button four;

    private List<Button> buttonList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuBar.setUseSystemMenuBar(
                System.getProperty("os.name") != null && System.getProperty("os.name").startsWith("Mac"));

        setTextArea();
        setButtonList();
        addSearchListener();
    }

    /**
     * Adds a search listener that detects change in text in the search bar.
     * The listener in turn displays buttons ordered in alphabetical order.
     * 
     */
    private void addSearchListener() {
        searchBar.textProperty().addListener((ChangeListener<String>) (ov, t, t1) -> {
            if (!(searchBar.getText().equals(" ") || searchBar.getText().equals("")
                    || searchBar.getText().substring(0).contains("  ")
                            && searchBar.getText().substring(0, 1).equals(" "))) {

                System.out.println("Searching...");

                buttonsPane.setVisible(false);
                resultsPane.setVisible(true);

                GridPane grid = new GridPane();

                for (int i = 0; i < buttonList.size(); i++) {
                    if (buttonList.get(i).getText().toLowerCase().contains(searchBar.getText().toLowerCase())) {
                        grid.add(buttonList.get(i), 0, i);
                    }
                }

                resultsPane.setContent(grid);
            }

            else {
                resultsPane.setVisible(false);
                buttonsPane.setVisible(true);
            }
        });
    }

    private void setButtonList() {

        buttonList = new ArrayList<Button>();

        System.out.println(textArea.getText());
        System.out.println(one.getText());

        buttonList.add(one);
        buttonList.add(two);
        buttonList.add(three);
        buttonList.add(four);
    }

    /**
     * Sets up the text area of the editior scene to display what is currently held
     * in the text file upon start up.
     * 
     */
    private void setTextArea() {
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
                activateMainStage(currentStage);
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

                    textArea.setText(currentText += line + "\n");
                    originalText += line;
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the action of a code to the file. Called when the GCode and MCode
     * buttons are pressed.
     * 
     * @param code
     * @throws FileNotFoundException
     */
    private void writeCode(CodeBasic code) throws FileNotFoundException {
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
     * edited save immediately with zero doubt that whats displayed in the file is
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