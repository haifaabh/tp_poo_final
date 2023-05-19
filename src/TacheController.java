import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javax.swing.*;

public class TacheController {

    @FXML
    private TextField nomTache;

    @FXML
    private ChoiceBox<?> etat;

    @FXML
    private TextField type;

    @FXML
    private TextField couleur;

    @FXML
    private Button submit;

    @FXML
    private ChoiceBox<?> priorite;

    @FXML
    private DatePicker datelimite;

    @FXML
    private TextField nbrepitition;

    @FXML
    private TextField nbjours;

    @FXML
    private TextField duree;
    @FXML
    void introduireTache(ActionEvent event) {

    }

    @FXML
    private <LocalDate> void handleSubmitButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("test.fxml"));
        Parent root = loader.load();
        testController tacheEntrerController = loader.getController();


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

        String nom = nomTache.getText();
        Etat_realisation etatRealisation = (Etat_realisation) etat.getValue();
        LocalDate dateLimite = (LocalDate) datelimite.getValue();
        String categorieType = type.getText();
        String categorieCouleur = couleur.getText();
        Priorite prio = (Priorite) priorite.getValue();
        Categorie categorie=new Categorie(categorieType,categorieCouleur);
       int rep= Integer.parseInt(nbrepitition.getText());
        int nbjour= Integer.parseInt(nbjours.getText());
        int dur=Integer.parseInt(duree.getText());
Periodicite periodicite=new Periodicite(rep,nbjour);
Projet proj=testController.p;
        // Create an instance of TacheSimple and set its properties
    /*     TacheSimple tacheSimple = new TacheSimple(nom,etatRealisation,dur,dateLimite,categorie,prio,);
      tacheSimple.setNom(nomTache);
        tacheSimple.setEtatRealisation(etatRealisation);
        tacheSimple.setDateLimite(dateLimite);
        tacheSimple.setCategorie(categorie);
        tacheSimple.setPriorite(priorite);

        // Pass the TacheSimple instance to the second page controller
        secondPageController.setTacheSimple(tacheSimple);
*/

    }

    // ...

}
