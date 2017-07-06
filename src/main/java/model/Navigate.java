package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

import java.io.IOException;

/**
 * Created by mickael.afonso on 21/04/2017.
 */
public class Navigate {

    public void change(String file, Class c, String title){
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/src/main/view/Logins.fxml"));
            Group group = (Group) loader.load();
            fillPane(group, "PARAMÈTRE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillPane(Group group, String titre){

    }
}
