package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by Micka on 27/03/2017.
 */

public class User_menu {

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private void newUser(ActionEvent event) {
        Loader load = new Loader("/view/SetUser.fxml", "AJOUTER UN UTILISATEUR");
        SetUser setUser = load.getLoader().getController();
        setUser.setEdit(Enumeration.ADD);
        setUser.choice();
    }

    @FXML
    private void newUsers(ActionEvent event) {
        new Loader("/view/DragAndDrop.fxml", "AJOUTER DES UTILISATEURS");
    }

    @FXML
    private void search(ActionEvent event) {
        Loader load = new Loader("/view/Table.fxml", "RECHERCHER UN UTILISATEUR");
        Table table = load.getLoader().getController();
        table.setType("user");
        table.load();
    }
}