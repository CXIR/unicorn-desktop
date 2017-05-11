import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

import java.io.IOException;

/**
 * Created by Micka on 10/04/2017.
 */
public class Logins {
    private Menu menu;

    @FXML
    private void change(ActionEvent event) {
        /*try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Table.fxml"));
            Group add = (Group) loader.load();
            menu.fillPane(add, "RECHERCHER UN UTILISATEUR");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @FXML
    private void back(ActionEvent event) {
        /*try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Table.fxml"));
            Group add = (Group) loader.load();
            menu.fillPane(add, "RECHERCHER UN UTILISATEUR");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }
}
