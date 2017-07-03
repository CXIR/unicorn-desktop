package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        //users = re.getUsers();

        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        /*admin.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, Boolean>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, Boolean> param) {
                if (users.g)
                if(!param.getValue().get(colNo).equals(excelData.get(0).get(colNo))){
                    return new SimpleStringProperty((param.getValue().get(colNo)));
                }
                return null;
            }
        });*/
        admin.setCellFactory( new Callback<TableColumn<User,Boolean>, TableCell<User,Boolean>>() {
            @Override
            public TableCell<User,Boolean> call( TableColumn<User,Boolean> param ) {
                return new CheckBoxTableCell<User,Boolean>() {
                    {
                        setAlignment( Pos.CENTER );
                    }
                    @Override
                    public void updateItem( Boolean item, boolean empty ) {
                        if ( ! empty ) {
                            TableRow  row = getTableRow();

                            if ( row != null ) {
                                int rowNo = row.getIndex();
                                TableView.TableViewSelectionModel sm = getTableView().getSelectionModel();

                                if ( item )  sm.select( rowNo );
                                else  sm.clearSelection( rowNo );
                            }
                        }

                        super.updateItem( item, empty );
                    }
                };
            }
        } );
        superAd.setCellValueFactory(new PropertyValueFactory<>("superAd"));

        if(this.users != null){
            ObservableList<User> list = FXCollections.observableArrayList(users);
            table.setItems(list);
        }

        table.setEditable(false);
    }


}
