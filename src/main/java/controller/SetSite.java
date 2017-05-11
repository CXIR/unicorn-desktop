package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Created by mickael.afonso on 21/04/2017.
 */
public class SetSite {
    private Menu menu;
    private boolean edit;

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
    public void change(ActionEvent event){
        if(edit == false){

        }
        else{

        }
    }

    @FXML
    public void cancel(ActionEvent event){
        if(edit == false){

        }
        else{

        }
    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }
}
