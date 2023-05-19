import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class dashcont{

    @FXML
    private Button affiche;

    @FXML
    void afficher(ActionEvent event) {

    }
    public void afficher() throws IOException {
        Stage stage=    (Stage) affiche.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("man.fxml"));

        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
    }

}
