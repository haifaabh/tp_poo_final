package com.example.tp.Backend;

import java.io.Serializable;

public class Categorie implements Comparable<Categorie> , Serializable {
    String type,couleur;

    public Categorie(String type, String couleur) {
        this.type = type;
        this.couleur = couleur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String toString() {
        return type+" Couleur: "+couleur;
    }

    public int compareTo(Categorie cat)
    {
        if(type.equals(cat.getType())||couleur.equals(cat.getCouleur()))
            return 0;
        return type.compareTo(cat.getType());
    }

    public boolean equals(Categorie cat)
    {
        return type.equals(cat.getType()) || couleur.equals(cat.getCouleur());
    }
}

