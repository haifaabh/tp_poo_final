package com.example.tp.Backend;

import java.io.Serializable;

public class Creneau implements Serializable {
    private static int dureeMin =30;
    private String type;
    private int duree;
    private boolean bloque;
    private Tache tache;
    private int debut;
    private int fin;

    public Creneau(Tache tache, int debut, int fin) {
        this.tache = tache;
        this.debut = debut;
        this.fin = fin;
    }

    public void setType(String type)
    {
        this.type = type;
    }
    public void setBloque(boolean bloque) {
        this.bloque = bloque;
    }

    public Creneau(int duree)
    {
        this.duree = duree;
    }

    //Les getters
    public String getType()
    {
        return type;
    }

    public int getDuree()
    {
        return duree;
    }

    public boolean isBloque()
    {
        return bloque;
    }

    public Tache getTache()
    {
        return tache;
    }

    //Le constructeur
    public Creneau (int debut,int fin)throws DureeMinExeption
    {
        if((fin-debut)<dureeMin) throw new DureeMinExeption();
        this.debut = debut;
        this.fin = fin;
        duree = fin - debut;
        type = "libre";
        bloque = false;
    }

    public void ajouterTache(Tache tache)
    {
        this.tache=tache;
        type = "occupé";
    }

    public void afficherCreneau() {
        System.out.println("Type: " + type);
        System.out.println("Duree: " + duree +" minutes");
        System.out.println("Bloque: " + bloque);
        if (tache != null) {
            System.out.println("Tache: " + tache.getNom());
        } else {
            System.out.println("Tache: null");
        }
        System.out.print("Debut: " +debut/60+":"+debut%60);
        if(debut%60==0) System.out.println("0") ;
        else System.out.println(" ");
        System.out.print("Fin: " + fin/60+":"+fin%60);
        if(fin%60==0) System.out.println("0") ;
        else System.out.println(" ");
    }

    public Creneau decomposer(int debut,int fin,Tache tache) throws DureeMinExeption
    {
        Creneau creneau = new Creneau(debut, fin);
        duree = duree - (fin - debut);
        if(duree<dureeMin) throw new DureeMinExeption();
        creneau.ajouterTache(tache);
        return creneau;
    }

    public Creneau decomposer(int duree,Tache tache)throws DureeMinExeption
    {
        Creneau creneau = new Creneau(this.duree-duree);
        if(creneau.getDuree()<dureeMin) throw new DureeMinExeption();
        this.duree = duree;
        ajouterTache(tache);
        return creneau;
    }

    public boolean equals(Creneau creneau)
    {
        if((type.equals(creneau.getType()))&&(bloque==creneau.isBloque())&&(duree==creneau.getDuree()))
        {
            if((tache!=null)&&(creneau.getTache()!=null))
            {
                if(tache.equals(creneau.getTache()))
                    return true;
            }
            else
                return true;
        }
        return false;
    }

    public void suppTache()
    {
        tache = null;
        type = "libre";
    }

    public void rendreLibre()
    {
        type = "libre";
    }

    public int getDebut() {
        return debut;
    }

    public int getFin() {
        return fin;
    }

    public static void setDureeMin(int dureeMin) {
        Creneau.dureeMin = dureeMin;
    }
}

