package com.example.tp.Backend;

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
        System.out.println("creneau duree : "+creneau.getDuree());
        System.out.println("tache duree : "+this.getDuree());
        if (creneau.getType().equals("libre") && creneau.getDuree() >= this.getDuree()) {
            System.out.println("yooo?");
            if (creneau.getDuree() == this.getDuree() ){
                creneau.ajouterTache(this);
                creneau.afficherCreneau();
            }
            else{
                try {
                    creneau=creneau.decomposer(creneau.getDebut(),creneau.getDebut()+this.getDuree(), this);
                    return creneau;
                } catch (DureeMinExeption e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    public void replanifier(Creneau creneau) {
        this.planifier(creneau);
    }

}