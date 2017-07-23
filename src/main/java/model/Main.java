package model;

import controller.Connection;
import controller.Menu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Micka on 07/03/2017.
 */
public class Main extends Application {
    public static Stage primaryStage;
    private Scene scene;
    private AnchorPane pane;

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Partage ta Caisse");
        connection();
    }

    public void connection(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Connection.fxml"));
            pane = (AnchorPane) loader.load();
            Connection control = loader.getController();
            control.setMain(this);

            scene = new Scene(pane);
            primaryStage.setScene(scene);
            //primaryStage.setFullScreen(true);
            //primaryStage.setResizable(false);
            //primaryStage.minWidthProperty().bind(scene.widthProperty());
            //primaryStage.minHeightProperty().bind(scene.heightProperty());
            primaryStage.setMinHeight(650);
            primaryStage.setMinWidth(1000);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sample(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Menu.fxml"));
            pane = (AnchorPane) loader.load();
            Menu control = loader.getController();
            control.setMain(this);

            scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.setMinHeight(650);
            primaryStage.setMinWidth(1000);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        launch(args);
    }
}
