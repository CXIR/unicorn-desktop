package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by mickael.afonso on 21/04/2017.
 */
public class SetSite implements Initializable {
    private Menu menu;
    private String edit;

    @FXML
    private Label nameDi;

    @FXML
    private TextField nameAd;

    @FXML
    private Label addDi;

    @FXML
    private TextField addAd;

    @FXML
    private Label postDi;

    @FXML
    private TextField postAd;

    @FXML
    private Button valid;

    @FXML
    private Button cancel;

    public void setEdit(String edit){
        this.edit = edit;
    }


    @Override
    public void initialize(URL url, ResourceBundle re){
        if(edit != null){
            choice();
        }
    }

    @FXML
    public void change(ActionEvent event){
        switch (edit){
            case "add":
                edit = "display";
                display();
                break;

            case "change":

                edit = "display";
                display();
                break;

            case "display":
                edit = "change";
                change();
                break;
        }
    }

    @FXML
    public void back(ActionEvent event){
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Site.fxml"));
            Group group = (Group) loader.load();
            Site controller = loader.getController();
            controller.setMenu(menu);
            menu.fillPane(group, "GESTION DES SITES");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void choice(){
        switch(edit){
            case "add":
                break;
            case "change":
                change();
                break;
            case "display":
                display();
                break;
        }
    }


    public void change(){
        changeForm(nameAd, nameDi);
        changeForm(addAd, addDi);
        changeForm(postAd, postDi);

        valid.setText("Valider");
        cancel.setText("Annuler");
    }

    public void display(){
        changeForm(nameAd, nameDi);
        changeForm(addAd, addDi);
        changeForm(postAd, postDi);

        valid.setText("Modifier");
        cancel.setText("Précédent");
    }

    public void changeForm(TextField text, Label label){
        switch (edit){
            case "add":
                text.setVisible(true);
                label.setVisible(false);
                break;
            case "change":
                text.setText(label.getText());
                text.setVisible(true);
                label.setVisible(false);
                break;
            case "display":
                if(text.getText() != null){
                    label.setText(text.getText());
                }
                text.setVisible(false);
                label.setVisible(true);
                break;
        }
    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }
}
