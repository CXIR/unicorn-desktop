package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import java.io.IOException;

/**
 * Created by Micka on 27/03/2017.
 */

public class User extends Menu {
    private Menu menu;

    public User(){
        this.menu = Menu.menu;
    }

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private void newUser(ActionEvent event) {
        //controller.SetUser addUser = getLoader("/controller.SetUser.fxml").;
        
        //fillPane(change("/view/SetUser.fxml"), "AJOUTER UN UTILISATEUR");
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/SetUser.fxml"));
            Group add = (Group) loader.load();
            SetUser setUser = loader.getController();
            setUser.setMenu(menu);
            setUser.setEdit("add");
            setUser.choice();
            menu.fillPane(add, "AJOUTER UN UTILISATEUR");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void newUsers(ActionEvent event) {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/DragAndDrop.fxml"));
            Group add = (Group) loader.load();
            DragAndDrop drop = loader.getController();
            drop.setMenu(menu);
            menu.fillPane(add, "AJOUTER DES UTILISATEURS");
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
            menu.fillPane(add, "RECHERCHER UN UTILISATEUR");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }
}