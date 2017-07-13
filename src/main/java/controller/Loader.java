package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import model.User;

import java.io.IOException;

/**
 * Created by mickael.afonso on 17/05/2017.
 */
public class Loader {
    private FXMLLoader loader;

    public Loader(String fxml, String title){
        try {
            this.loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxml));
            Group group = (Group) loader.load();
            Menu.menu.fillPane(group, title);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Erreur fichier FXML");
        }
    }

    public FXMLLoader getLoader(){
        return loader;
    }
}
