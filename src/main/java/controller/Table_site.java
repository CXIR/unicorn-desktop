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
import model.Site;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by mickael.afonso on 18/05/2017.
 */
public class Table_site implements Initializable {
    private ArrayList<Site> sites;

    @FXML
    private TextField txt;

    @FXML
    private TableView<Site> table;

    @FXML
    private TableColumn<Site, String> name;

    @FXML
    private TableColumn<Site, String> adress;

    @FXML
    private TableColumn<Site, String> postalCode;

    @FXML
    private TableColumn<Site, String> city;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Site sites = new Site();
        this.sites = sites.getSites();
        setTable();
    }

    public void setTable(){
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        adress.setCellValueFactory(new PropertyValueFactory<>("adress"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));

        if(this.sites != null){
            ObservableList<Site> list = FXCollections.observableArrayList(sites);
            table.setItems(list);
        }

        table.setEditable(false);

        table.setRowFactory(tableView -> {
            TableRow<Site> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())){
                    Loader load = new Loader("/view/SetSite.fxml", "SITE : " + row.getItem().getName());
                    SetSite setSite = load.getLoader().getController();
                    setSite.setSite(row.getItem());
                    setSite.setEdit(Enumeration.DISPLAY);
                    setSite.choice();
                }
            });
            return  row;
        });
    }

    @FXML
    public void search(ActionEvent event){
        if (sites != null && txt != null) {
            for (int i = table.getItems().size() - 1; i >= 0; i--) {
                if (table.getColumns().get(0).getCellData(i).toString().toLowerCase().contains(txt.getText().toLowerCase()) == false) {
                    table.getItems().remove(i);
                }
            }
        }
    }

    @FXML
    public void cancel(ActionEvent event){
        if (sites != null){
            table.getItems().clear();
            txt.setText("");
            setTable();
        }
    }
}
