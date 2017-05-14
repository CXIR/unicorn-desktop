package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Micka on 27/03/2017.
 */
public class SetUser implements Initializable {
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


    @Override
    public void initialize(URL url, ResourceBundle re){
        if(edit != null){
            choice();
        }
    }

    @FXML
    private void validate(ActionEvent event) {
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
        changeForm(firstAd, firstDi);
        changeForm(mailAd, mailDi);

        //dateAd.setValue(LocalDate(dateDi.getText()));
        dateDi.setVisible(false);
        dateAd.setVisible(true);
        LocalDate date = LocalDate.parse(dateDi.getText());
        dateAd.setValue(date);
        placeAd.setValue(placeDi.getText());
        placeDi.setVisible(false);
        placeAd.setVisible(true);
        placeAd.getSelectionModel().select(placeDi.getText());
        valid.setText("Valider");
        cancel.setText("Annuler");
    }

    public void display(){
        changeForm(nameAd, nameDi);
        changeForm(firstAd, firstDi);
        changeForm(mailAd, mailDi);
        if(dateAd.getValue() != null){
            Date date = Date.from(dateAd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = dateFormat.format(date);
            dateDi.setText(strDate);
        }
        dateDi.setVisible(true);
        dateAd.setVisible(false);
        if(placeAd.getValue() != null){
            placeDi.setText(placeAd.getSelectionModel().getSelectedItem().toString());
        }
        placeDi.setVisible(true);
        placeAd.setVisible(false);
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
