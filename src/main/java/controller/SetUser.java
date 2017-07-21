package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import model.Request;
import model.Site;
import model.User;
import model.Verifications;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Micka on 27/03/2017.
 */
public class SetUser implements Initializable {
    private Enumeration edit;
    private User user;

    @FXML
    private GridPane grid;

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

    /*@FXML
    private Label desc;

    @FXML
    private Label phone;*/

    @FXML
    private Label siteDi;

    @FXML
    private ComboBox<Site> siteAd;

    @FXML
    private Button valid;

    @FXML
    private Button cancel;

    @FXML
    private Button suppr;

    private Label descTitle;

    private Label desc;

    private Label phoneTitle;

    private Label phone;

    public void setEdit(Enumeration edit){
        this.edit = edit;
    }


    @Override
    public void initialize(URL url, ResourceBundle re){
        user = new User();
        if(edit != null){
            choice();
        }
        Site site = new Site();
        siteAd.getItems().addAll(site.getSites());

        siteAd.setConverter(new StringConverter() {
            @Override
            public String toString(Object object) {
                Site site = (Site) object;
                return site.getName();
            }

            @Override
            public Object fromString(String string) {
                return null;
            }
        });
        descTitle = new Label("Description :");
        desc = new Label();
        phoneTitle = new Label("Telephone :");
        phone = new Label();
    }

    @FXML
    private void validate(ActionEvent event) {
        Verifications verif = new Verifications();
        if (edit == edit.ADD || edit == edit.CHANGE) {
            if (verif.isNotEmpty(nameAd.getText()) && verif.isNotEmpty(firstAd.getText()) && verif.isNotEmpty(dateAd.getValue().toString()) && verif.isNotEmpty(mailAd.getText()) && verif.isNotEmpty(siteAd.getSelectionModel().getSelectedItem().toString())) {

                user.setLastname(nameAd.getText());
                user.setFirstname(firstAd.getText());
                Date date = Date.from(dateAd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                user.setBirthdate(date);
                user.setMailAdress(mailAd.getText());
                user.setSite(siteAd.getSelectionModel().getSelectedItem());


                if (edit == edit.ADD) {
                    user.createUser();
                } else {
                    System.out.println(user.getFirstname());
                    user.updateUser();
                    user.updateSite();
                }
                setEdit(edit.DISPLAY);
                display();
            }
            else {
                new Message("L'un des champs est vide");
            }
        }
        else {
            setEdit(edit.CHANGE);
            change();
        }
    }

    @FXML
    private void back(ActionEvent event) {
        User_menu user_menu = User_menu.user_menu;
        Loader loader = new Loader("/view/User.fxml","GESTION DES UTILISATEURS");
        User_menu user_m = loader.getLoader().getController();

        if (user_menu != null){
            if (user_menu.getButtons() != null) {
                for (Button button : user_menu.getButtons()) {
                    user_m.addButton(button);
                }
            }
        }
    }

    @FXML
    public void delete(ActionEvent event){
        user.deleteUser();
    }

    public void choice(){
        switch(edit){
            case ADD:
                break;
            case CHANGE:
                change();
                break;
            case DISPLAY:
                display();
                break;
        }
    }


    public void change(){
        nameAd.setText(user.getLastname());
        firstAd.setText(user.getFirstname());
        Date dt =  user.getBirthdate();
        LocalDate date = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dateAd.setValue(date);
        mailAd.setText(user.getMailAdress());
        siteAd.getItems().addAll();
        siteAd.getSelectionModel().select(user.getSite());
        grid.getChildren().removeAll(desc, descTitle, phone, phoneTitle);

        setForm(true);
        setDisp(false);
        valid.setText("Valider");
        cancel.setText("Annuler");
    }

    public void display(){
        nameDi.setText(user.getLastname());
        firstDi.setText(user.getFirstname());
        dateDi.setText(user.getStrDate());
        mailDi.setText(user.getMailAdress());
        if (user.getSite() != null) {
            siteDi.setText(user.getSite().getName());
        }
        desc.setText(user.getDescription());
        phone.setText(user.getPhoneNumber());
        grid.add(descTitle, 0, 5);
        grid.add(desc, 1, 5);
        grid.add(phoneTitle, 0, 6);
        grid.add(phone, 1, 6);

        setForm(false);
        setDisp(true);

        valid.setText("Modifier");
        cancel.setText("Précédent");
    }

    public void setForm(boolean b){
        grid.setVgap(30);
        nameAd.setVisible(b);
        firstAd.setVisible(b);
        dateAd.setVisible(b);
        mailAd.setVisible(b);
        siteAd.setVisible(b);
        desc.setVisible(b);
        phone.setVisible(b);
    }

    public void setDisp(boolean b){
        grid.setVgap(15);
        nameDi.setVisible(b);
        firstDi.setVisible(b);
        dateDi.setVisible(b);
        mailDi.setVisible(b);
        siteDi.setVisible(b);
        desc.setVisible(b);
        phone.setVisible(b);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
