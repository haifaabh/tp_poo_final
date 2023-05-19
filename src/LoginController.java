import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView logo;

    @FXML
    private TextField psudo;

    @FXML
    private ImageView logo2;

    @FXML
    private Button loginButton;

    @FXML
    void initialize() {
        Image image  = new Image(getClass().getResourceAsStream("calendar.png"));
        logo.setImage(image);
        logo2.setImage(image);
        assert psudo != null : "fx:id=\"psudo\" was not injected: check your FXML file 'Login.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'Login.fxml'.";

    }
}
