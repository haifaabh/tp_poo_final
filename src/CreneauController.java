import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CreneauController {
    @FXML
    private Label typeLabel;

    @FXML
    private Label dureeLabel;

    @FXML
    private Label bloqueLabel;

    @FXML
    private Label tacheLabel;

    @FXML
    private Label debutLabel;

    @FXML
    private Label finLabel;

    private Creneau creneau;

    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
        updateUI();
    }

    private void updateUI() {
        typeLabel.setText(creneau.getType());
        dureeLabel.setText(String.valueOf(creneau.getDuree()));
        bloqueLabel.setText(String.valueOf(creneau.isBloque()));
        if (creneau.getTache() != null) {
            tacheLabel.setText(creneau.getTache().getNom());
        } else {
            tacheLabel.setText("null");
        }
        debutLabel.setText(String.valueOf(creneau.getDebut()));
        finLabel.setText(String.valueOf(creneau.getFin()));
    }
}
