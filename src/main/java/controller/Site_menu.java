package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Created by mickael.afonso on 07/04/2017.
 */
public class Site_menu {
    @FXML
    private void newSite(ActionEvent event) {
        Loader load = new Loader("/view/SetSite.fxml", "AJOUTER UN SITE");
        SetSite setSite = load.getLoader().getController();
        setSite.setEdit(Enumeration.ADD);
        setSite.choice();
    }


    @FXML
    private void search(ActionEvent event) {
        new Loader("/view/table_site.fxml", "RECHERCHER UN SITE");
    }
}
