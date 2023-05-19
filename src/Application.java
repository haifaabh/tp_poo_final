package com.example.tp.Backend;
import java.util.TreeMap;

    public class App
    {
        TreeMap<String,Utilisateur> utilisateurs = new TreeMap<String,Utilisateur>();

        public App(String pseudo)
        {
            utilisateurs.put(pseudo,new Utilisateur(pseudo));
        }
        public void creerCompte(String pseudo)
        {
            if(utilisateurs.containsKey(pseudo))
                System.out.println("Ce pseudo existe déja!!");
            else
            {
                Utilisateur utilisateur = new Utilisateur(pseudo);
                utilisateurs.put(pseudo, utilisateur);
                System.out.println("Utilisateur crée avec succès!!");
            }

        }

        public Utilisateur authentifier(String pseudo)
        {
            if(utilisateurs.containsKey(pseudo))
            {
                System.out.println("Bienvenue "+pseudo+"!!");
                Utilisateur user = utilisateurs.get(pseudo);
                return user;
            }
            else
            {
                System.out.println("Utilisateur inexistant!!");
                return null;
            }
        }
    }

