package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

import java.io.IOException;

/**
 * Created by mickael.afonso on 07/04/2017.
 */
public class Site {
    private Menu menu;

    @FXML
    private void newSite(ActionEvent event) {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/SetSite.fxml"));
            Group add = (Group) loader.load();
            menu.fillPane(add, "AJOUTER UN SITE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void search(ActionEvent event) {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Table.fxml"));
            Group add = (Group) loader.load();
            //add.getChildren().remove();
            menu.fillPane(add, "RECHERCHER UN SITE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }
}
