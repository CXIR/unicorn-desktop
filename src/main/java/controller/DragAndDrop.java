package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import model.Main;
import model.ReadFile;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by mickael.afonso on 07/04/2017.
 */
public class DragAndDrop extends Menu {
    private ArrayList<File> files = new ArrayList<File>();

    @FXML
    private Label name;

    @FXML
    private Button search;

    @FXML
    private Rectangle drop;

    @FXML
    private StackPane stack;

    @FXML
    private void searchFile(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner le fichier");
        File file = fileChooser.showOpenDialog(Main.primaryStage);
        if(file != null){
            //String extension = file.getName().substring(file.getName().indexOf('.') + 1,file.getName().length());

        }
    }

    @FXML
    private void dragFile(DragEvent event){
        Dragboard db = event.getDragboard();
        if(db.hasFiles()){
            event.acceptTransferModes(TransferMode.LINK);
        }
        event.consume();
    }

    @FXML
    private void dropFile(DragEvent event){
        Dragboard db = event.getDragboard();
        if(db.hasFiles()){
            name.setText("");
            files.clear();
            for (File file : db.getFiles()) {
                //String extension = file.getName().substring(file.getName().indexOf('.') + 1,file.getName().length());
                String ext = extension(file);
                if(ext.equals("txt") || ext.equals("xml") || ext.equals("csv")){
                    files.add(file);
                    name.setText(name.getText() + ", " + file.getName());
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("Le type de fichier est invalide.");
                    alert.showAndWait();
                }
            }
        }
        event.consume();
    }

    @FXML
    private void validate(ActionEvent event){
        if(files != null){
            for(File file : files){
                ReadFile read = new ReadFile(file, extension(file));
            }
        }
        new Loader("/view/User.fxml", "GESTION DES UTILISATEURS");
    }

    @FXML
    private void cancel(ActionEvent event){
        new Loader("/view/User.fxml", "GESTION DES UTILISATEURS");
    }

    public String extension(File file){
        return file.getName().substring(file.getName().indexOf('.') + 1,file.getName().length());
    }
}
