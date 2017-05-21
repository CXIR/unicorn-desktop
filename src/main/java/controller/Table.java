package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
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
        switch (type){
            case "user":
                users.add(new User(1,"m", "d", "12/05/2017", "d", "d"));
                box.getChildren().add(new Table_user(users));
                break;
            case "site":
                sites.add(new Site(1, "n", "ad", "post"));
                box.getChildren().add(new Table_site(sites));
                break;
        }

    }

}
