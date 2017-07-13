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
import model.User;
import org.json.simple.parser.ParseException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Micka on 29/06/2017.
 */
public class Table_report implements Initializable {
    private ArrayList<Report> reports;

    @FXML
    private TextField txt;

    @FXML
    private TableView<Report> table;

    @FXML
    private TableColumn<Report, String> reported;

    @FXML
    private TableColumn<Report, String> plaintiff;

    @FXML
    private TableColumn<Report, String> message;

    @FXML
    private TableColumn<Report, Boolean> bloqued;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Report reports = new Report();
        try {
            this.reports = reports.getReports();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        setTable();
    }

    public void setTable(){
        reported.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Report, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Report, String> param) {
                if (param.getValue() != null){
                    return new SimpleStringProperty(param.getValue().getReported().getFirstname() + " " + param.getValue().getReported().getLastname());
                }
                return null;
            }
        });

        plaintiff.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Report, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Report, String> param) {
                if (param.getValue() != null){
                    return new SimpleStringProperty(param.getValue().getPlaintiff().getFirstname() + " " + param.getValue().getPlaintiff().getLastname());
                }
                return null;
            }
        });

        message.setCellValueFactory(new PropertyValueFactory<>("message"));

        bloqued.setCellValueFactory(new PropertyValueFactory<>("bloqued"));

        bloqued.setCellFactory( new Callback<TableColumn<Report,Boolean>, TableCell<Report,Boolean>>() {
            @Override
            public TableCell<Report,Boolean> call( TableColumn<Report,Boolean> param ) {
                return new CheckBoxTableCell<Report,Boolean>() {
                    @Override
                    public void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            TableRow  row = getTableRow();

                            if (row != null) {
                                Report report = param.getTableView().getItems().get(row.getIndex());
                                if (item){
                                    report.setBloqued(true);
                                    report.getReported().updateStatus(4);
                                }
                                else{
                                    report.setBloqued(false);
                                    report.getReported().updateStatus(1);
                                }
                                //Request req = new Request("post", "/users/modify/" + param.getTableView().getItems().get(rowNo).getId());
                            }
                        }
                    }
                };
            }
        } );

        if(this.reports != null){
            ObservableList<Report> list = FXCollections.observableArrayList(reports);
            table.setItems(list);
        }

        table.setEditable(true);


    }

    @FXML
    public void search(ActionEvent event){
        if (reports != null && txt != null) {
            for (int i = table.getItems().size() - 1; i >= 0; i--) {
                if (table.getColumns().get(0).getCellData(i).toString().toLowerCase().contains(txt.getText().toLowerCase()) == false) {
                    table.getItems().remove(i);
                }
            }
        }
    }

    @FXML
    public void cancel(ActionEvent event){
        if (reports != null){
            table.getItems().clear();
            txt.setText("");
            setTable();
        }
    }
}
