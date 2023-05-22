package com.example.tp.Backend;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Tache implements Serializable {
    private String nom;
    private Etat_realisation etatRealisation;
    private int duree;
    private Priorite priorite;
    private LocalDateTime dateLimite;
    private Categorie categorie;
    private Projet projet;

    public Tache(String nom, Etat_realisation etatRealisation, int duree, LocalDateTime dateLimite, Categorie categorie, Priorite priorite, Projet projet) {
        this.nom = nom;
        this.etatRealisation = etatRealisation;
        this.duree = duree;
        this.dateLimite = dateLimite;
        this.categorie = categorie;
        this.priorite = priorite;
        this.projet = projet;
    }

    public Tache(String nom) {
        this.nom = nom;
    }

    public Tache() {
    }

    public void setCategorie(Categorie categorie)
    {
        this.categorie = categorie;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Etat_realisation getEtatRealisation() {
        return etatRealisation;
    }
    public void setEtatRealisation(Etat_realisation etatRealisation) {
        this.etatRealisation = etatRealisation;
    }
    public int getDuree() {
        return duree;
    }

    public Projet getProjet(){return projet;}

    public void setDuree(int duree) {
        this.duree = duree;
    }
    public Priorite getPriorite() {
        return priorite;
    }
    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }
    public LocalDateTime getDateLimite() {
        return dateLimite;
    }
    public void setDateLimite(LocalDateTime dateLimite) {
        this.dateLimite = dateLimite;
    }

    public boolean equals(Tache tache)
    {
        if((nom.equals(tache.getNom()))&&(categorie.equals(tache.getCategorie())))
            return true;
        return false;
    }

    private Categorie getCategorie() {
        return categorie;
    }

    public void reporter(LocalDateTime date)
    {
        dateLimite = date;
    }
}
