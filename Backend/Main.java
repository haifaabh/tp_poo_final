package com.example.tp.Backend;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        App app= new App();
        //Menu
        //Creer un compte ou se connecter
        System.out.println("Bienvenue !!! Avez vous un compte? o/n");
        String res = scan.next();
        System.out.print("Veuillez entrer votre pseudo : ");
        String pseudo = scan.next();
        Utilisateur utilisateur;
        if(res.equals("o"))
        {
            utilisateur = app.authentifier(pseudo);
        } else if (res.equals("n"))
        {
            app.creerCompte(pseudo);
            utilisateur = app.authentifier(pseudo);
        }
        else
        {
            System.out.println("Vous avez entrer un choix inexistant!!");
            return;
        }
        //Choisir quoi faire apres
        System.out.println("****MENU****");
        System.out.println("1-Modifier la durée minimale d'un creneau");
        utilisateur.setMinDureeCreneau(scan.nextInt());

        System.out.println("2-Modifier le nombre minimal de taches a faire dans la journée");
        utilisateur.modifier_nb_min(scan.nextInt());

        System.out.println("3-Afficher le calendrier");
        //utilisateur.afficherCalendrier();

        System.out.println("6-Ajouter une categorie");
        app.ajouterCategorie(utilisateur);

        System.out.println("7-Afficher mes categories");
        utilisateur.afficherCategories();

        System.out.println("8-Fixer les créneau libres de votre planning");
        //app.creerPlanning(utilisateur);
        utilisateur.afficherCalendrier();

        System.out.println("4-Planifier une tache simple manuellement");
        //app.planifierTacheSimple(utilisateur);


        System.out.println("5-Planifier une tache automatiquement");
        app.planifierSimpleAuto(utilisateur);
        utilisateur.afficherCalendrier();

        System.out.println("6-Introduire l'etat d'une tache");
        app.quitter();
    }
}
