package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Status;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Micka on 18/07/2017.
 */
public class SetStatus implements Initializable {
    private Enumeration edit;
    private Status status;
    @FXML
    private Label nameDi;

    @FXML
    private TextField nameAd;

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
        status = new Status();
        if(edit != null){
            choice();
        }
    }

    @FXML
    public void validate(ActionEvent event){
        if (edit == edit.ADD || edit == edit.CHANGE){
            status.setLabel(nameAd.getText());

            if (edit == edit.ADD){
                status.createStatus();
            }
            else{
                status.updateStatus();
            }
            if (!status.isInvalid()) {
                setEdit(edit.DISPLAY);
                display();
            }
        }
        else{
            setEdit(edit.CHANGE);
            change();
        }
    }

    @FXML
    public void back(ActionEvent event){
        new Loader("/view/Status.fxml", "GESTION DES STATUS");
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
        nameAd.setText(status.getLabel());

        setForm(true);
        setDisp(false);
        valid.setText("Valider");
        cancel.setText("Annuler");
        suppr.setVisible(false);
    }

    public void display(){
        nameDi.setText(status.getLabel());

        setForm(false);
        setDisp(true);

        valid.setText("Modifier");
        cancel.setText("Précédent");
        suppr.setVisible(true);
    }

    public void setForm(boolean b){
        nameAd.setVisible(b);
    }

    public void setDisp(boolean b){
        nameDi.setVisible(b);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
