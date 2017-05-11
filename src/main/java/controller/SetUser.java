package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Micka on 27/03/2017.
 */
public class SetUser {
    private Menu menu;
    private String edit;

    @FXML
    private Label nameDi;

    @FXML
    private TextField nameAd;

    @FXML
    private Label firstDi;

    @FXML
    private TextField firstAd;

    @FXML
    private Label dateDi;

    @FXML
    private DatePicker dateAd;

    @FXML
    private Label mailDi;

    @FXML
    private TextField mailAd;

    @FXML
    private Label placeDi;

    @FXML
    private ComboBox placeAd;

    @FXML
    private Button valid;

    @FXML
    private Button cancel;

    public void setEdit(String edit){
        this.edit = edit;
    }


    /*@FXML
    public void initialize(){
        if(edit.equals("change")){
            change();
        }
        else if(edit.equals("display")){
            display();
        }
    }*/

    @FXML
    private void validate(ActionEvent event) {
        switch (edit){
            case "add":

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

        /*try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/User.fxml"));
            Group group = (Group) loader.load();
            User controller = loader.getController();
            controller.setMenu(menu);
            menu.fillPane(group, "GESTION DES UTILISATEURS");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/User.fxml"));
            Group group = (Group) loader.load();
            User controller = loader.getController();
            controller.setMenu(menu);
            menu.fillPane(group, "GESTION DES UTILISATEURS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void change(){
        changeForm(nameAd, nameDi);
        changeForm(firstAd, firstDi);
        changeForm(mailAd, mailDi);

        //dateAd.setValue(LocalDate(dateDi.getText()));
        dateDi.setVisible(true);
        dateAd.setVisible(false);
        placeAd.setValue(placeDi.getText());
        placeDi.setVisible(true);
        placeAd.setVisible(false);
    }

    public void display(){
        changeForm(nameAd, nameDi);
        changeForm(firstAd, firstDi);
        changeForm(mailAd, mailDi);

        dateDi.setText(dateAd.toString());
        dateDi.setVisible(true);
        dateAd.setVisible(false);
        placeDi.setText(placeAd.getPromptText());
        placeDi.setVisible(true);
        placeAd.setVisible(false);
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
