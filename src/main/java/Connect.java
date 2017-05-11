import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by Micka on 07/03/2017.
 */
public class Connect {
    private Main main;

    @FXML
    private Label title;

    @FXML
    private TextField login;

    @FXML
    private TextField pass;

    @FXML
    private Button admins;

    @FXML
    private void buttonConn(ActionEvent event) throws IOException {
        if(login.getText().equals("admin") && pass.getText().equals("1234")){
            /*Stage stage;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Menu.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            loader.setController(new Menu());
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(pane, stage.getWidth(), stage.getHeight());
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.centerOnScreen();
            stage.show();*/
            main.sample();

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Les identifiants ne sont pas valides");
            alert.showAndWait();
            login.setText("");
            pass.setText("");
        }
    }

    public void setMain(Main main){
        this.main = main;
    }
}
