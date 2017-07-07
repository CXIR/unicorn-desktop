package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
/*public class Table extends Menu implements Initializable {
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

    @FXML
    private TextField txt;

    private TableView table;
    @Override
    public void initialize(URL url, ResourceBundle re){

        if(type != null){
            load();
        }
    }

    public void load(){
        switch (type){
            case "user":
                User users = new User();
                table = new Table_user(users.getUsers());
                box.getChildren().add(table);
                break;
            case "site":
                Site sites = new Site();
                table = new Table_site(sites.getStites());
                box.getChildren().add(table);
                break;
        }

    }


    /*@FXML
    public void search(ActionEvent event){
        if (users != null && txt != null) {
            for (int i = table.getItems().size() - 1; i >= 0; i--) {
                if (table.getColumns().get(0).getCellData(i).toString().toLowerCase().contains(txt.getText().toLowerCase()) == false) {
                    table.getItems().remove(i);
                }
            }
        }
    }

    @FXML
    public void cancel(ActionEvent event){
        if (users != null){
            table.getItems().clear();
            txt.setText("");
            box.getChildren().clear();
            load();
        }
    }

}*/
