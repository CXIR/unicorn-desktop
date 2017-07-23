package controller;

import PluginManager.PluginLoader;
import annotation.Method;
import annotation.Modification;
import annotation.Parameter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Micka on 27/03/2017.
 */

public class User_menu implements Initializable {
    public static User_menu user_menu;
    private ArrayList<Button> buttons;

    @FXML
    public VBox box;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (user_menu == null){
            user_menu = this;
        }
    }

    @Method(
            auteur = "MA",
            date = "17/05/2017",
            nomDeLaMethode = "newUSer",
            portee = "private",
            typeRetour = "void",
            description = "Redirige l'utilisateur vers la page de création d'un nouvel utiisateur",
            parametres =
                    {
                            @Parameter(
                                    type = "ActionEvent",
                                    nom = "event",
                                    description = "Evenement"
                            )
                    },
            modifications =
                    {
                            @Modification(
                                    auteur = "AED",
                                    dateModification = "27/05/2017",
                                    descriptionModification = "Ajout de l'annotation"
                            )
                    }
    )
    @FXML
    private void newUser(ActionEvent event) {
        Loader load = new Loader("/view/SetUser.fxml", "AJOUTER UN UTILISATEUR");
        SetUser setUser = load.getLoader().getController();
        setUser.setEdit(Enumeration.ADD);
        setUser.choice();
    }

    @Method(
            auteur = "MA",
            date = "17/05/2017",
            nomDeLaMethode = "search",
            portee = "private",
            typeRetour = "void",
            description = "Redirige l'utilisateur vers la page de recherche d'un utilisateur",
            parametres =
                    {
                            @Parameter(
                                    type = "ActionEvent",
                                    nom = "event",
                                    description = "Evenement"
                            )
                    },
            modifications =
                    {
                            @Modification(
                                    auteur = "AED",
                                    dateModification = "27/05/2017",
                                    descriptionModification = "Ajout de l'annotation"
                            )
                    }
    )
    @FXML
    private void search(ActionEvent event) {
        new Loader("/view/table_user.fxml", "RECHERCHER UN UTILISATEUR");
    }

    public void addButton(Button button){
        buttons = new ArrayList<>();
        this.box.getChildren().add(button);
        buttons.add(button);
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }
}