package controller;

import javafx.scene.control.Alert;

/**
 * Created by Micka on 19/07/2017.
 */
public class Message {
    public Message(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
