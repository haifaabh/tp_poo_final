package com.example.tp.Backend;

import java.io.Serializable;
import java.util.ArrayList;

public class Projet implements Serializable {
    private String nom;
    private String description;
    private ArrayList<Tache> taches = new ArrayList<Tache>();

    public Projet(String nom, String description, ArrayList<Tache> taches) {
        this.nom = nom;
        this.description = description;
        this.taches = taches;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public Etat_realisation evaluer( Tache taches[]) {
        Etat_realisation etat=null;
        int maxOccurence = 0;
        for (int i=0;i<taches.length;i++)
        {
            int occurrence=1;
            for(int j=i+1;j<taches.length;j++){
                if(taches[i].equals(taches[j])){
                    occurrence++;
                }
            }

            if(occurrence>maxOccurence){
                maxOccurence=occurrence;
                etat=taches[i].getEtatRealisation();
            }
        }
        return etat;
    }

}

