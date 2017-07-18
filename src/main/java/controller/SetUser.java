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

    @FXML
    private Label desc;

    @FXML
    private Label phone;

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
    }

    @FXML
    private void validate(ActionEvent event) {
        if (edit == edit.ADD || edit == edit.CHANGE){
            user.setLastname(nameAd.getText());
            user.setFirstname(firstAd.getText());
            Date date = Date.from(dateAd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            /*SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = dateFormat.format(date);*/
            user.setBirthdate(date);
            user.setMailAdress(mailAd.getText());
            user.setSite(siteAd.getSelectionModel().getSelectedItem());


            if (edit == edit.ADD){
                user.createUser();
                user.updateSite();
            }
            else{
                System.out.println(user.getFirstname());
                user.updateUser();
                user.updateSite();
                /*dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                user.setBirth(dateFormat.format(date));
                Request req = new Request("post", "/users/modify/" + user.getId());
                req.putUser(user);*/
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
