package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import model.Request;
import model.Site;
import model.User;
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
    private Label siteDi;

    @FXML
    private ComboBox<Site> siteAd;

    @FXML
    private Button valid;

    @FXML
    private Button cancel;

    public void setEdit(Enumeration edit){
        this.edit = edit;
    }


    @Override
    public void initialize(URL url, ResourceBundle re){
        user = new User();
        if(edit != null){
            choice();
        }
        Request req = new Request("get", "/site/");
        siteAd.getItems().addAll(req.getSites());

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
    }

    @FXML
    private void validate(ActionEvent event) {
        System.out.println(nameAd.getText());
        if (edit == edit.ADD || edit == edit.CHANGE){
            user.setName(nameAd.getText());
            user.setFirst(firstAd.getText());
            Date date = Date.from(dateAd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = dateFormat.format(date);
            user.setBirth(strDate);
            user.setMail(mailAd.getText());
            user.setSite(siteAd.getSelectionModel().getSelectedItem());


            if (edit == edit.ADD){
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                user.setBirth(dateFormat.format(date));
                Request req = new Request("post", "/users/new");
                req.putUser(user);
            }
            else{
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                user.setBirth(dateFormat.format(date));
                Request req = new Request("post", "/users/modify/" + user.getId());
                req.putUser(user);
            }
            setEdit(edit.DISPLAY);
            display();
        }
        else{
            setEdit(edit.CHANGE);
            change();
        }
    }

    @FXML
    private void back(ActionEvent event) {
        new Loader("/view/User.fxml", "GESTION DES UTILISATEURS");
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
        nameAd.setText(user.getName());
        firstAd.setText(user.getFirst());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(user.getBirth(), format);
        dateAd.setValue(date);
        mailAd.setText(user.getMail());
        siteAd.getItems().addAll();
        siteAd.getSelectionModel().select(user.getSite());

        setForm(true);
        setDisp(false);
        valid.setText("Valider");
        cancel.setText("Annuler");
    }

    public void display(){
        nameDi.setText(user.getName());
        firstDi.setText(user.getFirst());
        dateDi.setText(user.getBirth());
        mailDi.setText(user.getMail());
        siteDi.setText(user.getSite().getName());

        setForm(false);
        setDisp(true);

        valid.setText("Modifier");
        cancel.setText("Précédent");
    }

    public void setForm(boolean b){
        nameAd.setVisible(b);
        firstAd.setVisible(b);
        dateAd.setVisible(b);
        mailAd.setVisible(b);
        siteAd.setVisible(b);
    }

    public void setDisp(boolean b){
        nameDi.setVisible(b);
        firstDi.setVisible(b);
        dateDi.setVisible(b);
        mailDi.setVisible(b);
        siteDi.setVisible(b);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
