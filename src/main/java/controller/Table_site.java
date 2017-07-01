package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Site;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mickael.afonso on 18/05/2017.
 */
public class Table_site extends TableView<Site> {
    private ArrayList<Site> sites;
    private TableColumn<Site, String> name;
    private TableColumn<Site, String> address;
    private TableColumn<Site, Integer> postal;
    private TableColumn<Site, String> city;

    public Table_site(ArrayList<Site> sites){
        this.sites = sites;
        name = new TableColumn("Nom");
        address = new TableColumn("Adresse");
        postal = new TableColumn("Code postal");
        city = new TableColumn("Ville");
        this.getColumns().addAll(name, address, postal, city);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        postal.setCellValueFactory(new PropertyValueFactory<>("postal"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));

        if(this.sites != null){
            ObservableList<Site> list = FXCollections.observableArrayList(sites);
            this.setItems(list);
        }

        this.setEditable(false);

        this.setRowFactory(tableView -> {
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
}
