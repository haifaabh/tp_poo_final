import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



import java.awt.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
 /* public class Main extends Application {


    public static void main(String[] args) {
      launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       TacheSimple t=new TacheSimple("cooking");

        Creneau cren=new Creneau(t,20,21);
        ArrayList<Creneau> liste=new ArrayList<>();
        liste.add(cren);
        Jour j=new Jour(liste);
        ArrayList<Jour> liste1=new ArrayList<>();
        liste1.add(j);
        Planning plan=new Planning(liste1);
        ArrayList<Planning> liste2=new ArrayList<>();
        liste2.add(plan);
        Calendrier cal=new Calendrier(liste2);
        Utilisateur user=new Utilisateur("djihene",cal);
        controllerman.user=user;

                Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();

    }
    FXMLLoader loader = new FXMLLoader(getClass().getResource("creneau.fxml"));
    Parent root = loader.load();
    Projet projet =new Projet("projet1","description1");
    Categorie categorie=new Categorie("study","bleu");
    Tache tache =new Tache("study",Etat_realisation.En_Cours,12, LocalDateTime.now().plusDays(7),categorie,Priorite.High,projet) ;

    CreneauController controller = loader.getController();
    Creneau creneau = new Creneau(tache, 9, 12); // Create a sample Creneau object
        controller.setCreneau(creneau);

       stage.setScene(new Scene(root));
        stage.show();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("date_picker.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Date Picker Example");
        primaryStage.show();

}}*/
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

 /*       FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
        Parent root = loader.load();
        testController firstPageController = loader.getController();

        // Set up event handlers or pass any necessary data to the controller

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
*/
    public static void main(String[] args) {
        launch(args);
    }
}

