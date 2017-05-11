package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Created by mickael.afonso on 05/04/2017.
 */
public class Admin {
    private Menu menu;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private void newAdmin(ActionEvent event) {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/AddAdmin.fxml"));
            Group add = (Group) loader.load();
            menu.fillPane(add, "AJOUTER UN ADMINISTARTEUR");
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
            menu.fillPane(add, "RECHERCHER UN ADMINISTRATEUR");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }
}
