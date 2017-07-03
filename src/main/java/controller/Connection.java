package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Micka on 07/03/2017.
 */
public class Connection implements Initializable {
    private Main main;

    @FXML
    private Label title;

    @FXML
    private TextField login;

    @FXML
    private TextField pass;

    @FXML
    private Button admins;

    @FXML
    private BorderPane border;

    @Override
    public void initialize(URL url, ResourceBundle re){
        //border.prefHeightProperty().bind(Main.primaryStage.heightProperty());
        //border.prefWidthProperty().bind(Main.primaryStage.widthProperty());
    }

    @FXML
    private void buttonConn(ActionEvent event) throws IOException {
        if(login.getText().equals("admin") && pass.getText().equals("1234")){
            main.sample();

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Les identifiants ne sont pas valides");
            alert.showAndWait();
            login.setText("");
            pass.setText("");
            login.requestFocus();
        }
    }

    public void setMain(Main main){
        this.main = main;
    }
}
