import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Created by Micka on 27/03/2017.
 */

public class User {
    private Menu menu;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private void newUser(ActionEvent event) {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/AddUser.fxml"));
            Group add = (Group) loader.load();
            AddUser addUser = loader.getController();
            addUser.setMenu(menu);
            menu.fillPane(add, "AJOUTER UN UTILISATEUR");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void newUsers(ActionEvent event) {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/DragAndDrop.fxml"));
            Group add = (Group) loader.load();
            DragAndDrop drop = loader.getController();
            drop.setMenu(menu);
            menu.fillPane(add, "AJOUTER UN UTILISATEURS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void search(ActionEvent event) {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Table.fxml"));
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