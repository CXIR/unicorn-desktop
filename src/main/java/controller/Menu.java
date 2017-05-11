package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import model.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Micka on 07/03/2017.
 */
public class Menu implements Initializable {
    protected Main main;
    protected static Menu menu;

    public Menu(){
        this.menu = this;
    }

    @FXML
    protected Label title;

    @FXML
    protected Label home;

    @FXML
    protected Label users;

    @FXML
    protected Label admins;

    @FXML
    protected Label sites;

    @FXML
    protected Label param;

    @FXML
    protected Label offline;

    @FXML
    protected Rectangle rect;

    @FXML
    protected BorderPane pane;

    @FXML
    protected Pane pane2;

    @FXML
    protected SplitPane split;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        //pane2.prefWidthProperty().bind(split.widthProperty());
        //split.lookupAll(".split-pane-divider").stream().forEach(div ->  div.setMouseTransparent(true) );
        //split.setDividerPositions(0.2204);
    }

    @FXML
    private void labelHome(MouseEvent event) {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Home.fxml"));
            Group group = (Group) loader.load();
            Home controller = loader.getController();
            controller.setMenu(this);
            fillPane(group, "PARTAGE TA CAISSE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void labelUsers(MouseEvent event) {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/User.fxml"));
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
            loader.setLocation(getClass().getResource("/view/Admin.fxml"));
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
            loader.setLocation(getClass().getResource("/view/Site.fxml"));
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
            loader.setLocation(getClass().getResource("/view/Logins.fxml"));
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

    public Group change(String file){
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource(file));
            Group group = (Group) loader.load();
            loader.getController();
            /*classe view.controller = Class.forName(classe);
            classe.getSuperclass() view.controller = loader.getController();
            view.controller.setMenu(this);*/
            //Class c = loader.getController();
            return group;
            //fillPane(group, title);
        } catch (IOException e) {
            throw new IllegalStateException("Erreur fichier FXML");
        } /*catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    public void fillPane(Group group, String titre) {
        pane.getChildren().clear();
        title.setText(titre);
        StackPane root = new StackPane(rect, group);
        pane.setTop(title);
        pane.setCenter(root);
    }
}
