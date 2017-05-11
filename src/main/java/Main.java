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
            loader.setLocation(getClass().getResource("/Connection.fxml"));
            pane = (AnchorPane) loader.load();
            Connect controller = loader.getController();
            controller.setMain(this);
            scene = new Scene(pane);
            primaryStage.setScene(scene);
            //primaryStage.setFullScreen(true);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sample(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Menu.fxml"));
            pane = (AnchorPane) loader.load();
            Menu controller = loader.getController();
            controller.setMain(this);
            scene = new Scene(pane);
            primaryStage.setScene(scene);
            //primaryStage.setFullScreen(true);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        launch(args);
    }
}
