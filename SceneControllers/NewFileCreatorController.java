package SceneControllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;


public class NewFileCreatorController extends CreatorController {
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
}
