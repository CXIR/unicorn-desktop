import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

/**
 * Created by Micka on 07/03/2017.
 */
public class Menu {
    private Main main;

    @FXML
    private Label title;

    @FXML
    private Label home;

    @FXML
    private Label users;

    @FXML
    private Label admins;

    @FXML
    private Label sites;

    @FXML
    private Label param;

    @FXML
    private Label offline;

    @FXML
    protected Rectangle rect;

    @FXML
    protected BorderPane pane;

    @FXML
    private void labelHome(MouseEvent event) {
        pane.getChildren().clear();
        title.setText("ACCUEIL");
        pane.setTop(title);
        pane.setCenter(rect);
    }

    @FXML
    private void labelUsers(MouseEvent event) {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/User.fxml"));
            Group group = (Group) loader.load();
            User controller = loader.getController();
            controller.setMenu(this);
            fillPane(group, "GESTION DES UTILISATEURS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void labelAdmins(MouseEvent event) {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Admin.fxml"));
            Group group = (Group) loader.load();
            Admin controller = loader.getController();
            controller.setMenu(this);
            fillPane(group, "GESTION DES ADMINISTRATEURS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void labelSites(MouseEvent event) {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Site.fxml"));
            Group group = (Group) loader.load();
            Site controller = loader.getController();
            controller.setMenu(this);
            fillPane(group, "GESTION DES SITES");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void labelParam(MouseEvent event) {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Logins.fxml"));
            Group group = (Group) loader.load();
            Logins controller = loader.getController();
            controller.setMenu(this);
            fillPane(group, "PARAMÈTRE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void labelOff(MouseEvent event) {
        main.connection();
    }

    public void setMain(Main main){
        this.main = main;
    }

    public void fillPane(Group group, String titre) {
        pane.getChildren().clear();
        title.setText(titre);
        StackPane root = new StackPane(rect, group);
        pane.setTop(title);
        pane.setCenter(root);
    }
}
