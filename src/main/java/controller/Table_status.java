package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Status;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Micka on 03/07/2017.
 */
public class Table_status implements Initializable {
    private ArrayList<Status> status;

    @FXML
    private TextField txt;

    @FXML
    private TableView<Status> table;

    @FXML
    private TableColumn<Status, String> name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Status status = new Status();
        this.status = status.getAllStatus();
        setTable();
    }

    public void setTable(){
        name.setCellValueFactory(new PropertyValueFactory<>("label"));

        if(this.status != null){
            ObservableList<Status> list = FXCollections.observableArrayList(status);
            table.setItems(list);
        }

        table.setEditable(false);

        table.setRowFactory(tableView -> {
            TableRow<Status> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())){
                    Loader load = new Loader("/view/SetStatus.fxml", "STATUS : " + row.getItem().getLabel());
                    SetStatus setStatus = load.getLoader().getController();
                    setStatus.setStatus(row.getItem());
                    setStatus.setEdit(Enumeration.DISPLAY);
                    setStatus.choice();
                }
            });
            return  row;
        });
    }

    @FXML
    public void search(ActionEvent event){
        if (status != null && txt != null) {
            for (int i = table.getItems().size() - 1; i >= 0; i--) {
                if (table.getColumns().get(0).getCellData(i).toString().toLowerCase().contains(txt.getText().toLowerCase()) == false) {
                    table.getItems().remove(i);
                }
            }
        }
    }

    @FXML
    public void cancel(ActionEvent event){
        if (status != null){
            table.getItems().clear();
            txt.setText("");
            setTable();
        }
    }
}
