package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Created by Micka on 18/07/2017.
 */
public class Status_menu {
    @FXML
    private void newStatus(ActionEvent event) {
        Loader load = new Loader("/view/SetStatus.fxml", "AJOUTER UN STATUS");
        SetStatus setStatus = load.getLoader().getController();
        setStatus.setEdit(Enumeration.ADD);
        setStatus.choice();
    }


    @FXML
    private void search(ActionEvent event) {
        new Loader("/view/table_status.fxml", "RECHERCHER UN STATUS");
    }
}
