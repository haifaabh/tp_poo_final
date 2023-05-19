import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDate;

public class testController {

    @FXML
    private DatePicker date;

    @FXML
    private TextField debut;

    @FXML
    private TextField fin;

    @FXML
    private TextField description;

    @FXML
    private TextField nomprojet;

     public  void TacheEntrer() throws IOException {

         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TacheEntrer.fxml"));
         Parent root = fxmlLoader.load();
         Stage primaryStage=new Stage();
         primaryStage.setScene(new Scene(root));
         primaryStage.setTitle("Introduire tache");
         primaryStage.show();
    }
static Projet p;



    @FXML
    private <LocalDate> void handleSuivantButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TacheEntrer.fxml"));
        Parent root = loader.load();
        TacheController tacheController = loader.getController();

        LocalDate day= (LocalDate) date.getValue();
        int start= Integer.parseInt(debut.getText());
        int finish=Integer.parseInt(fin.getText());
        String descr= description.getText();
        String nom= nomprojet.getText();

Projet projet=new Projet(descr,nom);
p=projet;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // ...

}



