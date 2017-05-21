package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Site;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Created by mickael.afonso on 21/04/2017.
 */
public class SetSite implements Initializable {
    private Enumeration edit;
    private Site site;
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

    public void setEdit(Enumeration edit){
        this.edit = edit;
    }


    @Override
    public void initialize(URL url, ResourceBundle re){
        if(edit != null){
            choice();
        }
    }

    @FXML
    public void validate(ActionEvent event){
        if (edit == edit.ADD || edit == edit.CHANGE){
            site.setName(nameAd.getText());
            site.setAddress(addAd.getText());
            site.setPostal(postAd.getText());

            setEdit(edit.DISPLAY);
            display();
        }
        else{
            setEdit(edit.CHANGE);
            change();
        }
    }

    @FXML
    public void back(ActionEvent event){
        new Loader("/view/Site.fxml", "GESTION DES SITES");
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
        nameAd.setText(site.getName());
        addAd.setText(site.getAddress());
        postAd.setText(site.getPostal());

        setForm(true);
        setDisp(false);
        valid.setText("Valider");
        cancel.setText("Annuler");
    }

    public void display(){
        nameDi.setText(site.getName());
        addDi.setText(site.getAddress());
        postDi.setText(site.getPostal());

        setForm(false);
        setDisp(true);

        valid.setText("Modifier");
        cancel.setText("Précédent");
    }

    public void setForm(boolean b){
        nameAd.setVisible(b);
        addAd.setVisible(b);
        postAd.setVisible(b);
    }

    public void setDisp(boolean b){
        nameDi.setVisible(b);
        addDi.setVisible(b);
        postDi.setVisible(b);
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
