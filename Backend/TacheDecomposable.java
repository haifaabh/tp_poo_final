package com.example.tp.Backend;

import java.util.*;
public class TacheDecomposable extends Tache{
    ArrayList<TacheSimple> taches=new ArrayList<TacheSimple>();

    public ArrayList<TacheSimple> getTaches() {
        return taches;
    }

    public void setTaches(ArrayList<TacheSimple> taches) {
        this.taches = taches;
    }

    public Etat_realisation evaluer() {
        Etat_realisation etat = null;
        int maxOccurence = 0;
        for (int i = 0; i < taches.size(); i++) {
            int occurrence = 1;
            for (int j = i + 1; j < taches.size(); j++) {
                if (taches.get(i).equals(taches.get(j))) {
                    occurrence++;
                }
            }
            if (occurrence > maxOccurence) {
                maxOccurence = occurrence;
                etat = taches.get(i).getEtatRealisation();
            }
        }
        return etat;
    }

    public void planifier(ArrayList< Creneau> creneaux) {
        int j=0;
        for (int i = 0; i < taches.size(); i++) {
            TacheSimple tache = taches.get(i);
            tache.planifier(creneaux.get(j));
            j++;
        }
    }


    public void replanifier(ArrayList< Creneau> creneaux) {
        this.planifier(creneaux);
    }

    public void ordonnerTaches() {  //from the highest to the lowest
        Collections.sort(taches, new Comparator<Tache>() {
            @Override
            public int compare(Tache t1, Tache t2) {
                return t2.getPriorite().compareTo(t1.getPriorite());
            }
        });
    }
}

