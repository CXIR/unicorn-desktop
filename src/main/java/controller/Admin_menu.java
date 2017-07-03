package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by mickael.afonso on 05/04/2017.
 */
public class Admin_menu {
    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private void newAdmin(ActionEvent event) {
        new Loader("/view/AddAdmin.fxml", "AJOUTER UN ADMINISTARTEUR");
    }

    @FXML
    private void search(ActionEvent event) {
        new Loader("/view/Table.fxml", "RECHERCHER UN ADMINISTRATEUR");
    }
}
