import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class SidebarController {

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Button Profile;

    @FXML
    private Button Planning;

    @FXML
    private Button Calendrier;
    @FXML
    Pane mainpage;


    public void afficherProfile() {
        showPage("");
    }

    public void afficherPlanning( ) {
        showPage("test.fxml");
    }

    public void afficherCalendrier() {
        showPage("");
    }

    private void showPage(String fxmlfile) {
        try {
            Parent page = FXMLLoader.load(getClass().getResource(fxmlfile));
            mainpage.getChildren().clear();
            mainpage.getChildren().add(0,page);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}