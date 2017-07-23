package controller;

import PluginManager.PluginLoader;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Main;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.jar.JarFile;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

/**
 * Created by Micka on 13/07/2017.
 */
public class Plugins implements Initializable {
    private ArrayList<File> files = new ArrayList<>();
    private ObservableList<File> list;

    @FXML
    private TableView table;

    @FXML
    private TableColumn col;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File directory = new File("./plugin");

        for (File file : directory.listFiles()){
            if (file.isFile() && extension(file)){
                files.add(file);
            }
        }

        if(files != null){
            list = FXCollections.observableArrayList(files);
            table.setItems(list);
        }

        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<File, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<File, String> param) {
                if (param.getValue() != null){
                    return new SimpleStringProperty(param.getValue().getName());
                }
                return null;
            }
        });
    }

    /**
     * Ajoute un plugin au projet
     */
    @FXML
    public void add(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner le fichier");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Executable Jar File", "*.jar"));
        File file = fileChooser.showOpenDialog(Main.primaryStage);
        if(file != null){
            File newFile = new File("./plugin/" + file.getName());
            try {
                Files.copy(file.toPath(), newFile.toPath());
                list.add(newFile);
                table.refresh();
            } catch (IOException e) {
                new Message("Le plugin a déjà été ajouté!");
            }

        }
    }

    /**
     * Supprime un plugin du projet
     */
    @FXML
    public void del(){
        Object obj = table.getSelectionModel().getSelectedItem();
        File file = (File) obj;
        PluginLoader.closeFile();
        if (file.delete()){
            System.out.println("Le fichier a bien été sauvegardé.");
        }
        else{
            System.out.println("Le fichier n'a pas ");
        }
        table.getItems().remove(obj);

        logout();
    }

    public void logout(){
        new Message("L'application doit redémarrer pour prendre en compte les modifications");
        try {
            Runtime run = Runtime.getRuntime();
            if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0){
                run.exec("cmd /c start launcher.bat");
            }
            else {
                run.exec("launcher.sh");
            }
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean extension(File file){
        if (file.getName().substring(file.getName().indexOf('.') + 1,file.getName().length()).equals("jar")){
            return true;
        }
        return false;
    }
}
