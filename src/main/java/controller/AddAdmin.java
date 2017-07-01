package controller;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Request;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by mickael.afonso on 07/04/2017.
 */
public class AddAdmin implements Initializable {
    @FXML
    private TextField txt;

    ArrayList<User> users;
    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, String> name;

    @FXML
    private TableColumn<User, Boolean> admin;

    @FXML
    private TableColumn<User, Boolean> superAd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Request re = new Request("get","/users/all");
<<<<<<< HEAD
        users = re.getUsers();
        setTable();
    }
=======
        //users = re.getUsers();
>>>>>>> refs/remotes/origin/master

    public void setTable(){
        name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                if (param.getValue() != null){
                    return new SimpleStringProperty(param.getValue().getName() + " " + param.getValue().getFirst());
                }
                return null;
            }
        });

        admin.setCellValueFactory(new PropertyValueFactory<>("admin"));

        admin.setCellFactory( new Callback<TableColumn<User,Boolean>, TableCell<User,Boolean>>() {
            @Override
            public TableCell<User,Boolean> call( TableColumn<User,Boolean> param ) {
                return new CheckBoxTableCell<User,Boolean>() {
                    @Override
                    public void updateItem(Boolean item, boolean empty) {
                        super.updateItem( item, empty );
                        if (!empty) {
                            TableRow  row = getTableRow();

                            if (row != null) {
                                int rowNo = row.getIndex();
                                TableView.TableViewSelectionModel select = getTableView().getSelectionModel();
                                if (item){
                                    param.getTableView().getItems().get(rowNo).setAdmin(true);
                                    param.getTableView().getItems().get(rowNo).setSuperAd(false);
                                }
                                else{
                                    param.getTableView().getItems().get(rowNo).setAdmin(false);
                                }
                                Request req = new Request("post", "/users/modify/" + param.getTableView().getItems().get(rowNo).getId());
                            }
                        }
                    }
                };
            }
        } );

        superAd.setCellValueFactory(new PropertyValueFactory<>("superAd"));

        superAd.setCellFactory( new Callback<TableColumn<User,Boolean>, TableCell<User,Boolean>>() {
            @Override
            public TableCell<User,Boolean> call( TableColumn<User,Boolean> param ) {
                return new CheckBoxTableCell<User,Boolean>() {
                    @Override
                    public void updateItem(Boolean item, boolean empty) {
                        super.updateItem( item, empty );
                        if (!empty) {
                            TableRow  row = getTableRow();

                            if (row != null) {
                                int rowNo = row.getIndex();
                                TableView.TableViewSelectionModel select = getTableView().getSelectionModel();
                                if (item){
                                    param.getTableView().getItems().get(rowNo).setAdmin(false);
                                    param.getTableView().getItems().get(rowNo).setSuperAd(true);
                                }
                                else{
                                    param.getTableView().getItems().get(rowNo).setSuperAd(false);
                                }
                            }
                        }
                    }
                };
            }
        } );

        if(this.users != null){
            ObservableList<User> list = FXCollections.observableArrayList(users);
            table.setItems(list);
        }

        table.setEditable(true);
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
