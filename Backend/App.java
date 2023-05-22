package com.example.tp.Backend;
import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.TreeMap;

    public class App {
        private TreeMap<String, Utilisateur> utilisateurs = new TreeMap<String, Utilisateur>();
        Scanner scan = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        public App(String pseudo) {
            utilisateurs.put(pseudo, new Utilisateur(pseudo));
        }

        public App(TreeMap<String, Utilisateur> utilisateurs)
        {
            this.utilisateurs = utilisateurs;
        }

        public App()
        {
            ObjectInputStream in;
            try{
                in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("App.dat"))));
                try
                {
                    utilisateurs = (TreeMap<String, Utilisateur>)in.readObject();
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();}

        }

        public void creerCompte(String pseudo) {
            if (utilisateurs.containsKey(pseudo))
                System.out.println("Ce pseudo existe déja!!");
            else {
                Utilisateur utilisateur = new Utilisateur(pseudo);
                utilisateurs.put(pseudo, utilisateur);
                System.out.println("Utilisateur crée avec succès!!");
            }

        }

        public Utilisateur authentifier(String pseudo) {
            if (utilisateurs.containsKey(pseudo)) {
                System.out.println("Bienvenue " + pseudo + "!!");
                Utilisateur user = utilisateurs.get(pseudo);
                return user;
            } else {
                System.out.println("Utilisateur inexistant!!");
                return null;
            }
        }

        public void creerPlanning(Utilisateur utilisateur) {
            if (utilisateurs.containsKey(utilisateur.getPseudo())) {
                System.out.println("Veuillez donner la date du premier jour de votre periode");
                LocalDate date = LocalDate.parse(scan.next(), formatter);
                LocalTime time = LocalTime.MIDNIGHT;
                LocalDateTime debutPeriode = LocalDateTime.of(date, time);
                System.out.println("Veuillez donner la date du dernier jour de votre periode");
                date = LocalDate.parse(scan.next(), formatter);
                time = LocalTime.MAX;
                LocalDateTime finPeriode = LocalDateTime.of(date, time);
                try {
                    Periode periode = new Periode(debutPeriode, finPeriode);
                    LocalDateTime jour = debutPeriode;
                    int[] debuts = new int[(int) ChronoUnit.DAYS.between(debutPeriode, finPeriode) + 1];
                    int[] fins = new int[(int) ChronoUnit.DAYS.between(debutPeriode, finPeriode) + 1];
                    int i = 0;
                    while (jour.isBefore(finPeriode)) {
                        System.out.println("Veuillez donner l'heure de debut de votre creneau libre le " + jour.toString());
                        LocalTime debut = LocalTime.parse(scan.next(), timeFormatter);
                        Duration debutMins = Duration.between(LocalTime.MIDNIGHT, debut);
                        debuts[i] = (int) debutMins.toMinutes();
                        System.out.println("Veuillez donner l'heure de fin de votre creneau libre le " + jour.toString());
                        LocalTime fin = LocalTime.parse(scan.next(), timeFormatter);
                        Duration finMins = Duration.between(LocalTime.MIDNIGHT, fin);
                        fins[i] = (int) finMins.toMinutes();
                        jour = jour.plusDays(1);
                        i++;
                    }

                    utilisateur.creerPlanning(debuts, fins, periode);
                } catch (DateException e) {
                    //Le message est déja dans le constructeur de l'exception
                } catch (DatesErronesException e) {
                    System.out.println("La date du début de période est situé aprés la fin de la période!!");
                }
            }
        }

        public Categorie ajouterCategorie(Utilisateur utilisateur) {
            if (utilisateurs.containsKey(utilisateur.getPseudo())) {
                String type = scan.next();
                System.out.println("Sa couleur ?:");
                String couleur = scan.next();
                utilisateur.ajouterCategorie(type, couleur);
                return new Categorie(type, couleur);
            }
            return null;
        }

        public void quitter() {
            ObjectOutputStream out;
            try
            {
                out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("App.dat"))));
                out.writeObject(utilisateurs);
                out.close();
                System.out.println("Au revoir!!");
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        public void planifierTacheSimple(Utilisateur utilisateur)
        {
            System.out.println("Veuillez entrer le nom de la tache");
            String nom = scan.next();
            System.out.println("Quel est l'état de réalisation de cette tache? : ");
            System.out.println("1-Complétée");
            System.out.println("2-Non réalisée");
            System.out.println("3-En cours");
            System.out.println("4-Annulée");
            System.out.println("5-Reportée");
            System.out.println("Veuillez choisir parmi ces choix ");
            int choix = scan.nextInt();
            Etat_realisation etat;
            switch (choix)
            {
                case 1 : etat = Etat_realisation.completed;
                    break;
                case 2 : etat = Etat_realisation.notRealized;
                    break;
                case 3 : etat = Etat_realisation.in_progress;
                    break;
                case 4 : etat = Etat_realisation.cancelled;
                    break;
                case 5 : etat = Etat_realisation.delayed;
                    break;
                default:
                {
                    System.out.println("Ce choix est inexistant , la tache sera donc par défaut classée non réalisée");
                    etat = Etat_realisation.notRealized;
                }
            }
            System.out.println("Veuillez entrer la durée : ");
            int duree = scan.nextInt();
            System.out.println("Veuillez préciser sa priorité de 1 à 3 tel que 1 est le plus prioritaire");
            choix = scan.nextInt();
            Priorite prio;
            switch (choix)
            {
                case 1 : prio = Priorite.High;
                    break;
                case 2 : prio = Priorite.Medium;
                    break;
                case 3 : prio = Priorite.Low;
                    break;
                default:
                {
                    System.out.println("Le numéro que vous avez entré ne correspond à aucun choix , la priorité est donc Low par défaut");
                    prio = Priorite.Low;
                }
            }
            System.out.println("Veuillez préciser la date limite également");
            LocalDate date = LocalDate.parse(scan.next(), formatter);
            LocalTime time = LocalTime.MIDNIGHT;
            LocalDateTime dateLimite = LocalDateTime.of(date, time);
            System.out.println("Ajoutez une catégorie à votre tache");
            Categorie cat = ajouterCategorie(utilisateur);
            System.out.println("Veuillez lui associer un projet s'il en a");
            System.out.println("Veuillez ajouter sa périodicité également (mettre un 0 si vous ne voulez pas)");
            int fois = scan.nextInt();
            System.out.println("Quel est sa fréquence?");
            int jours = scan.nextInt();
            Periodicite periodicite = new Periodicite(fois,jours);
            TacheSimple tache = new TacheSimple(nom,etat,duree,dateLimite,cat,prio,null,periodicite);
            System.out.println("Quel est la date de planification ?");
            date = LocalDate.parse(scan.next(), formatter);
            time = LocalTime.MIDNIGHT;
            LocalDateTime dateTache = LocalDateTime.of(date, time);
            System.out.println("Veuillez donner l'heure de debut de votre creneau");
            LocalTime debut = LocalTime.parse(scan.next(), timeFormatter);
            int debutMins =(int) Duration.between(LocalTime.MIDNIGHT, debut).toMinutes();
            System.out.println("Veuillez donner l'heure de fin de votre creneau");
            LocalTime fin = LocalTime.parse(scan.next(), timeFormatter);
            int finMins = (int) Duration.between(LocalTime.MIDNIGHT, fin).toMinutes();
            try
            {
                Creneau creneau = new Creneau(debutMins,finMins);
                utilisateur.planifierTacheSimple(tache,dateTache,creneau);
            } catch (DureeMinExeption e)
            {
                //Le message est déja affichée dans le constructeur de l'exception
            }
        }
    }
