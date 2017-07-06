package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import model.Request;
import model.Site;
import model.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Micka on 07/03/2017.
 */
public class Table extends Menu implements Initializable {
    private String type;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Site> sites = new ArrayList<>();

    public void setType(String type){
        this.type = type;
    }


    @FXML
    private TableView<User> table;

    @FXML
    private VBox box;

    @Override
    public void initialize(URL url, ResourceBundle re){

        if(type != null){
            load();
        }
    }

    public void load(){
        Request re;
        switch (type){
            case "user":
                re = new Request("get","/users/all");
                //box.getChildren().add(new Table_user(re.getUsers()));
                break;
            case "site":
                re = new Request("get","/site/");
                //sites.add(new Site(1, "n", "ad", "post"));
                //box.getChildren().add(new Table_site(re.getSites()));
                break;
        }

    }

}
