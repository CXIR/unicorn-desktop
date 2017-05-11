import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by mickael.afonso on 07/04/2017.
 */
public class DragAndDrop {
    private Menu menu;

    @FXML
    private Label name;

    @FXML
    private Button search;

    @FXML
    private Rectangle drop;

    @FXML
    private StackPane stack;

    @FXML
    private void searchFile(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner le fichier");
        File file = fileChooser.showOpenDialog(Main.primaryStage);
        if(file != null){
            //String extension = file.getName().substring(file.getName().indexOf('.') + 1,file.getName().length());

        }
    }

    @FXML
    private void dropFile(DragEvent event){
        event.acceptTransferModes(TransferMode.LINK);
        if(event.getEventType() == DragEvent.DRAG_OVER){
            event.se() = DragEvent.DRAG_DROPPED;
        }
        /*Dragboard db = event.getDragboard();
        if(db.hasFiles()){

            System.out.println("Ca drop mon pote");
            for (File file : db.getFiles()) {
                name.setText(file.getName());
            }
        }*/
        event.consume();
    }


    private void pageUser(){

    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }
}
