package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Micka on 10/04/2017.
 */
public class Logins implements Initializable {
    private boolean edit;

    @FXML
    private Label logDi;

    @FXML
    private TextField logAd;

    @FXML
    private Label passDi;

    @FXML
    private PasswordField passAd;

    @FXML
    private Button valid;

    @FXML
    private Button cancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backContent();
        logDi.setText(Connection.account.getMailAdress());
        crypt(Connection.account.getPassword());
    }

    @FXML
    private void change(ActionEvent event) {
        if(edit == false){
            edit = true;
            logAd.setText(logDi.getText());
            passAd.setText(passDi.getText());
            logDi.setVisible(false);
            passDi.setVisible(false);
            logAd.setVisible(true);
            passAd.setVisible(true);
            valid.setText("Valider");
        }
        else{
            if(logAd.getText() != null && passAd.getText() != null){
                Connection.account.setMailAdress(logAd.getText());
                Connection.account.setPassword(passAd.getText());
                Connection.account.updateUser();


                logDi.setText(logAd.getText());
                crypt(passAd.getText());
                backContent();
            }

            /*try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/controller.Table.fxml"));
            Group add = (Group) loader.load();
            menu.fillPane(add, "RECHERCHER UN UTILISATEUR");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        }
    }

    @FXML
    private void back(ActionEvent event) {
        if(edit == true){
            backContent();
        }
        else{
            new Loader("/view/Home.fxml", "PARTAGE TA CAISSE");
        }
    }

    @FXML
    private void clearPass(MouseEvent event){
        passAd.setText("");
    }

    public void crypt(String txt){
        StringBuffer word = new StringBuffer();
        for(int i = 0; i < txt.length(); i++){
            word.append("*");
        }
        passDi.setText(word.toString());
    }

    public void backContent(){
        edit = false;
        logDi.setVisible(true);
        logAd.clear();
        logAd.setVisible(false);
        passDi.setVisible(true);
        passAd.clear();
        passAd.setVisible(false);
        valid.setText("Modifier");
    }
}
