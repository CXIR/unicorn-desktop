import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by Micka on 27/03/2017.
 */
public class AddUser {
    private Menu menu;

    @FXML
    private void validate(ActionEvent event) {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/User.fxml"));
            Group group = (Group) loader.load();
            User controller = loader.getController();
            controller.setMenu(menu);
            menu.fillPane(group, "GESTION DES UTILISATEURS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/User.fxml"));
            Group group = (Group) loader.load();
            User controller = loader.getController();
            controller.setMenu(menu);
            menu.fillPane(group, "GESTION DES UTILISATEURS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }
}
