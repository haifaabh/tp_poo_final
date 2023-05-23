package com.example.tp.Backend;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Jour implements Serializable
{
    @Serial
    private static final long serialVersionUID = -3979269912217602497L;
    private LocalDateTime date;
    private int debutDuree;
    private int finDuree;
    private ArrayList<Creneau> creneaux = new ArrayList<Creneau>();

    public Jour(LocalDateTime date)
    {
        this.date = date;
    }

    public ArrayList<Creneau> getCreneaux()
    {
        return creneaux;
    }

    public LocalDateTime getDate()
    {
        return date;
    }

    public int getDebutDuree()
    {
        return debutDuree;
    }

    public int getFinDuree()
    {
        return finDuree;
    }

    public boolean equals(Jour jour)
    {
        return date.equals(jour.getDate());
    }

    public void ajouterCreneau(Creneau creneau)
    {
        creneaux.add(creneau);
    }

    public void ajouterTache(Tache tache,Creneau creneau){  //exception
        if((tache.getDateLimite()).compareTo(this.getDate())<0){
            Iterator<Creneau> it= creneaux.iterator();
            boolean trouv=false;
            while(it.hasNext()&&!trouv){
                Creneau cr = it.next();
                if(cr.equals(creneau)){
                    trouv=true;
                    cr.ajouterTache(tache);
                }
            }
            if(trouv==false){
                System.out.println("ce creneau n'existe pas dans la liste de creneaux de ce jour");
            }
        }
        else System.out.println("la date limite est superieur a la date de ce jour");

    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDebutDuree(int debutDuree) {
        this.debutDuree = debutDuree;
    }

    public void setFinDuree(int finDuree) {
        this.finDuree = finDuree;
    }

    public void setCreneaux(ArrayList<Creneau> creneaux) {
        this.creneaux = creneaux;
    }

    public String toString()
    {
        return date.toString();
    }

    public Tache getTache(Tache tache)
    {
        Iterator<Creneau> it= creneaux.iterator();
        while(it.hasNext())
        {
            Creneau cren = it.next();
            if(cren.getType().equals("occupé"))
            {
                if(tache.equals(cren.getTache()))
                    return cren.getTache();
            }
        }
        return null;
    }
}

