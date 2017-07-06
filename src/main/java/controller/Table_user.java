package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mickael.afonso on 15/05/2017.
 */
public class Table_user extends TableView<User> {
    private ArrayList<User> users;
    private TableColumn<User, String> name;
    private TableColumn<User, String> first;
    private TableColumn<User, String> birth;
    private TableColumn<User, String> mail;
    private TableColumn<User, String> place;

    public Table_user(ArrayList<User> users){
        this.users = users;
        name = new TableColumn("Nom");
        first = new TableColumn("Prénom");
        birth = new TableColumn("Date de naissance");
        mail = new TableColumn("Mail");
        place = new TableColumn("Lieu de Travail");
        this.getColumns().addAll(name, first, birth, mail, place);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        first.setCellValueFactory(new PropertyValueFactory<>("first"));
        birth.setCellValueFactory(new PropertyValueFactory<>("birth"));
        mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        place.setCellValueFactory(new PropertyValueFactory<>("namePlace"));

        if(!(this.users == null)){
            ObservableList<User> list = FXCollections.observableArrayList(users);
            this.setItems(list);
        }

        this.setEditable(false);

        this.setRowFactory(tableView -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())){
                    Loader load = new Loader("/view/SetUser.fxml", "UTILISATEUR : " + row.getItem().getLastname() + " " + row.getItem().getFirstname());
                    SetUser setUser = load.getLoader().getController();
                    setUser.setUser(row.getItem());
                    setUser.setEdit(Enumeration.DISPLAY);
                    setUser.choice();
                }
            });
            return  row;
        });
    }
}
