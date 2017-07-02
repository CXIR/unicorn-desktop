package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by mickael.afonso on 15/05/2017.
 */
public class Table_user implements Initializable {
    private ArrayList<User> users;

    @FXML
    private TextField txt;

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, String> first;

    @FXML
    private TableColumn<User, String> name;

    @FXML
    private TableColumn<User, String> birth;

    @FXML
    private TableColumn<User, String> mail;

    @FXML
    private TableColumn<User, String> site;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User users = new User();
        this.users = users.getUsers();
        setTable();
    }

    public void setTable(){
        first.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        name.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        birth.setCellValueFactory(new PropertyValueFactory<>("strDate"));
        mail.setCellValueFactory(new PropertyValueFactory<>("mailAdress"));
        site.setCellValueFactory(new PropertyValueFactory<>("nameSite"));

        if(this.users != null){
            ObservableList<User> list = FXCollections.observableArrayList(users);
            table.setItems(list);
        }

        table.setEditable(false);

        table.setRowFactory(tableView -> {
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

    @FXML
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
            setTable();
        }
    }
}
