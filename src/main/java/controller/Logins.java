package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * Created by Micka on 10/04/2017.
 */
public class Logins {
    private boolean edit;

    @FXML
    private Label label;

    @FXML
    private PasswordField pass;

    @FXML
    private Button valid;

    @FXML
    private Button cancel;

    @FXML
    private void change(ActionEvent event) {
        if(edit == false){
            edit = true;
            label.setVisible(false);
            pass.setVisible(true);
            valid.setText("Valider");
        }
        else{
            if(pass.getText() != null){
                StringBuffer word = new StringBuffer();
                for(int i = 0; i < pass.getText().length(); i++){
                    word.append("*");
                }
                label.setText(word.toString());
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

    public void backContent(){
        edit = false;
        label.setVisible(true);
        pass.clear();
        pass.setVisible(false);
        valid.setText("Modifier");
    }
}
