package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mickael.afonso on 07/04/2017.
 */
public class DragAndDrop {
    private Menu menu;
    private ArrayList<String> nameList = new ArrayList<String>();
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
            String extension = file.getName().substring(file.getName().indexOf('.') + 1,file.getName().length());

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
            nameList.clear();
            for (File file : db.getFiles()) {
                String extension = file.getName().substring(file.getName().indexOf('.') + 1,file.getName().length());
                System.out.println(extension);
                if(extension.equals("txt") || extension.equals("xml")){
                    nameList.add(file.getName());
                    name.setText(file.getName());
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
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/User.fxml"));
            Group group = (Group) loader.load();
            User controller = loader.getController();
            controller.setMenu(menu);
            menu.fillPane(group, "GESTION DES UTILISATEURS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancel(ActionEvent event){
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/User.fxml"));
            Group group = (Group) loader.load();
            User controller = loader.getController();
            controller.setMenu(menu);
            menu.fillPane(group, "GESTION DES UTILISATEURS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }
}
