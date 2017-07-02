package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Report;
import model.Vehicle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Micka on 30/06/2017.
 */
public class Table_vehicle implements Initializable {
    private ArrayList<Vehicle> vehicles;

    @FXML
    private TextField txt;

    @FXML
    private TableView<Vehicle> table;

    @FXML
    private TableColumn<Vehicle, String> user;

    @FXML
    private TableColumn<Vehicle, String> brand;

    @FXML
    private TableColumn<Vehicle, String> model;

    @FXML
    private TableColumn<Vehicle, String> registr;

    @FXML
    private TableColumn<Vehicle, Integer> place;

    @FXML
    private TableColumn<Vehicle, String> type;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Vehicle vehicles = new Vehicle();
        this.vehicles = vehicles.getVehicles();
        setTable();
    }

    public void setTable(){
        user.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vehicle, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vehicle, String> param) {
                if (param.getValue() != null){
                    return new SimpleStringProperty(param.getValue().getUser().getFirstname() + " " + param.getValue().getUser().getLastname());
                }
                return null;
            }
        });

        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));

        model.setCellValueFactory(new PropertyValueFactory<>("model"));

        registr.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));

        place.setCellValueFactory(new PropertyValueFactory<>("placesNumber"));

        type.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));

        if(this.vehicles != null){
            ObservableList<Vehicle> list = FXCollections.observableArrayList(vehicles);
            table.setItems(list);
        }

        table.setEditable(true);


    }

    @FXML
    public void search(ActionEvent event){
        if (vehicles != null && txt != null) {
            for (int i = table.getItems().size() - 1; i >= 0; i--) {
                if (table.getColumns().get(0).getCellData(i).toString().toLowerCase().contains(txt.getText().toLowerCase()) == false) {
                    table.getItems().remove(i);
                }
            }
        }
    }

    @FXML
    public void cancel(ActionEvent event){
        if (vehicles != null){
            table.getItems().clear();
            txt.setText("");
            setTable();
        }
    }

}
