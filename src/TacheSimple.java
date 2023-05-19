import java.sql.Date;
import java.time.*;

public class TacheSimple  extends Tache{
   Periodicite periodicite;

    public TacheSimple(String nom) {
        super(nom);
    }
//    public TacheSimple(int periodicite) {
//       this.periodicite = periodicite;
//    }

public TacheSimple(String nom, Etat_realisation etatRealisation, int duree, LocalDateTime dateLimite, Categorie categorie, Priorite priorite, Projet projet,Periodicite periodicite) {
    super(nom,etatRealisation,duree,dateLimite,categorie,priorite,projet);
    this.periodicite = periodicite;
}


public Periodicite getPeriodicite() {
    return periodicite;
}

public void setPeriodicite(Periodicite periodicite) {
    this.periodicite = periodicite;
} 

public void evaluer(Etat_realisation etat) {
   setEtatRealisation(etat);
}

public Creneau planifier(Creneau creneau) {
    if (creneau.getType() == "libre" && creneau.getDuree() >= this.getDuree()) {
        if (creneau.getDuree() == this.getDuree() ){ 
        creneau.ajouterTache(this);
        }
        else{
           creneau=creneau.decomposer(creneau.getDebut(),creneau.getDebut()+this.getDuree(), this);
        }
    }
    return creneau;
}

public void replanifier(Creneau creneau) {
    this.planifier(creneau);
}



}
