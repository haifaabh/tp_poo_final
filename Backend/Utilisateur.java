package com.example.tp.Backend;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Utilisateur implements Serializable {
    @Serial
    private static final long serialVersionUID = 9163008528982668413L;
    private String pseudo;
    private int nbMinTaches;
    private int nbTaches;
    private int joursConsec = 0;
    private LocalDateTime dateDerniere; //Afin de garder le nombre de jours consécutifs non érroné
    private Calendrier calendrier = new Calendrier();
    private Map<Badge, Integer> badges = Map.of(
            Badge.Good,0,
            Badge.Very_Good,0,
            Badge.Excellent,0
    );
    private Set<Categorie> categories = new TreeSet<Categorie>();
    private ArrayList<Projet> projets = new ArrayList<Projet>();
    private ArrayList<Planning> historique = new ArrayList<Planning>();
    private ArrayList<Tache> tachesAPlanifier = new ArrayList<Tache>();
    private int minDureeCreneau;

    public Utilisateur(String pseudo)
    {
        this.pseudo = pseudo;
    }

    public Utilisateur(String pseudo, Calendrier calendrier) {
        this.pseudo = pseudo;
        this.calendrier = calendrier;
    }

    public Utilisateur() {

    }

    public void introduireTache(Tache tache,LocalDateTime date,Creneau creneau) {
        boolean trouv = false;
        for (int i = 0; i < calendrier.getPlannings().size() && !trouv; i++) {

            Planning planning = calendrier.getPlanning(i);
            Iterator<Jour> jourIterator = planning.getJours().iterator();
            while (jourIterator.hasNext() && !trouv) {

                Jour jour = jourIterator.next();
                if (jour.getDate().equals(date)){
                    Iterator<Creneau> creneauIterator = jour.getCreneaux().iterator();
                    while (creneauIterator.hasNext() && !trouv) {
                        Creneau creneau1 = creneauIterator.next();
                        if (creneau1.equals(creneau)) {
                            trouv = true;
                            jour.ajouterTache(tache, creneau);
                            return;
                        }
                    }
                }
            }
        }
        if (!trouv) {
            System.out.println("cet creneau n'est pas dans votre calendrier.");
        }
    }

    public void introduireEtat(Tache tacheInitial,Etat_realisation etat)
    {
        Tache tache = calendrier.getTache(tacheInitial);
        tache.setEtatRealisation(etat);
        if(etat==Etat_realisation.completed)
        {
            nbTaches++;
            if (nbTaches == nbMinTaches) {
                System.out.println("Bravo !! Vous avez réussi à terminer "+ nbTaches +" taches Aujourd'hui!!");
                LocalDateTime today = LocalDateTime.now();
                if (!today.equals(dateDerniere))
                {
                    if (today.minusDays(1).equals(dateDerniere))
                    {
                        joursConsec++;
                        feliciter();
                    }
                    else
                        joursConsec = 1;
                    dateDerniere = today;
                }
            }
        }
    }

    public void setNb_Tache_Min(int nb_Tache_Min)
    {
        nbMinTaches = nb_Tache_Min;
    }

    public void modifier_nb_min(int nb_min)
    {
        setNb_Tache_Min(nb_min);
    }

    public void reporter(Tache tache ,LocalDateTime date , Planning planning,LocalDateTime dateLimite)
    {
        tache.setDateLimite(dateLimite);
        tache.setEtatRealisation(Etat_realisation.delayed);
        calendrier.reporter(tache,date, planning);
    }

    public void ajouterCategorie(String type , String couleur)
    {
        Categorie cat = new Categorie(type,couleur);
        categories.add(cat);
    }

    public void classer(Tache tache, Categorie cat)
    {
        tache.setCategorie(cat);
    }

    public void creerProjet(String nom , String description, ArrayList<Tache> taches )
    {
        Projet projet = new Projet(nom, description, taches);
        projets.add(projet);
    }

    public boolean valider(Planning planning, boolean valide)
    {
        if(valide)
        {
            calendrier.creerPlanning(planning.getPeriode(), planning.getJours());
            return true;
        }
        return false;
    }

    public void changerNom(Tache tache, String nom )
    {
        tache.setNom(nom);
    }

    public void bloquer(Creneau creneau )
    {
        creneau.setBloque(true);
    }

    public void suppTache(Tache tache , Planning planning)
    {
        planning.suppTache(tache);
    }

    public ArrayList<Jour> fixerCreneauLibre(int[] debuts, int[] fins , Periode periode)
    {
        LocalDateTime date = periode.getDateDebut();
        ArrayList<Jour> jours = new ArrayList<Jour>();
        int i=0;
        while(date.isBefore(periode.getDateFin()))
        {
            Jour jour = new Jour(date);
            int debut = debuts[i];
            int fin = fins[i];
            try
            {
                jour.ajouterCreneau(new Creneau(debut, fin));
            } catch (DureeMinExeption e)
            {
                //Il ya deja un message d'erreur dans l'exception
            }
            jours.add(jour);
            date = date.plusDays(1);
            i++;
        }
        return jours;
    }

    public void creerPlanning(int[] debuts,int[] fins , Periode periode)
    {
        ArrayList<Jour> jours = fixerCreneauLibre(debuts, fins, periode);
        calendrier.creerPlanning(periode, jours);
    }

    public void miseAJourPlanning(Planning planning)
    {
        calendrier.miseAJourPlanning(planning);
    }

    public Map<Etat_realisation, Integer> EtatsTaches()
    {
        LocalDateTime ceJour = LocalDateTime.now();
        Map<Etat_realisation, Integer> etats= calendrier.getEtats(ceJour);
        return etats;
    }

    public void feliciter()
    {
        if(joursConsec==5)
        {
            if(badges.get(Badge.Good)<3)
            {
                badges.put(Badge.Good, badges.get(Badge.Good)+1);
                System.out.println("Félicitation! Vous avez obtenu un badge Good!!");
            } else if (badges.get(Badge.Very_Good)<3)
            {
                badges.put(Badge.Very_Good, badges.get(Badge.Very_Good)+1);
                System.out.println("Félicitation! Vous avez obtenu un badge Very Good!!");
            }
            else
            {
                badges.put(Badge.Excellent, badges.get(Badge.Excellent)+1);
                System.out.println("Félicitation! Vous avez obtenu un badge Excellent!!");
            }
            joursConsec=0;
        }
    }

    public void PrioriserTache(Tache tache,Priorite priorite){
        boolean trouv = false;
        for (int i = 0; i < calendrier.getPlannings().size() && !trouv; i++) {

            Planning planning = calendrier.getPlanning(i);
            Iterator<Jour> jourIterator = planning.getJours().iterator();
            while (jourIterator.hasNext() && !trouv) {

                Jour jour = jourIterator.next();
                Iterator<Creneau> creneauIterator = jour.getCreneaux().iterator();
                while (creneauIterator.hasNext() && !trouv) {

                    Creneau creneau = creneauIterator.next();
                    if (creneau.getTache() != null && creneau.getTache().equals(tache)) {
                        trouv = true;
                        tache.setPriorite(priorite);
                        return;
                    }
                }
            }
        }
        if (!trouv) {
            System.out.println("cette tache n'est pas dans votre calendrier.");
        }
    }

    public void afficherCalendrier(){
        for (int i = 0; i < calendrier.getPlannings().size(); i++) {

            Planning planning = calendrier.getPlanning(i);
            Iterator<Jour> jourIterator = planning.getJours().iterator();
            while (jourIterator.hasNext() ) {
                Jour jour = jourIterator.next();
                System.out.println("Le jour :"+jour);
                Iterator<Creneau> creneauIterator = jour.getCreneaux().iterator();
                while (creneauIterator.hasNext()) {
                    Creneau creneau = creneauIterator.next();
                    creneau.afficherCreneau();
                }
            }
        }
    }

    /*public void afficherCalendrier(ListView<String> planningListView, ListView<String> jourListView, ListView<String> creneauListView) {
        // Clear the list views
        planningListView.getItems().clear();
        jourListView.getItems().clear();
        creneauListView.getItems().clear();

        // Iterate over the plannings in the calendrier
        for (int i = 0; i < calendrier.getPlannings().size(); i++) {
            Planning planning = calendrier.getPlanning(i);
            // Add the planning name to the planningListView
            planningListView.getItems().add(planning.toString());
            // Add a selection listener to the planningListView
            planningListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                // Clear the jourListView and creneauListView
                jourListView.getItems().clear();
                creneauListView.getItems().clear();

                // Find the selected planning
                Planning selectedPlanning = calendrier.getPlanning(planningListView.getSelectionModel().getSelectedIndex());
                // Iterate over the jours in the selected planning
                Iterator<Jour> jourIterator = selectedPlanning.getJours().iterator();
                while (jourIterator.hasNext()) {
                    Jour jour = jourIterator.next();
                    // Add the jour name to the jourListView
                    jourListView.getItems().add(jour.toString());
                    // Add a selection listener to the jourListView
                    jourListView.getSelectionModel().selectedItemProperty().addListener((observable1, oldValue1, newValue1) -> {
                        // Clear the creneauListView
                        creneauListView.getItems().clear();
                        // Find the selected jour
                        Jour selectedJour = selectedPlanning.getJour(jourListView.getSelectionModel().getSelectedIndex());;
                        // Iterate over the creneaux in the selected jour
                        Iterator<Creneau> creneauIterator = selectedJour.getCreneaux().iterator();
                        while (creneauIterator.hasNext()) {
                            Creneau creneau = creneauIterator.next();
                            // Add the creneau name to the creneauListView
                            creneauListView.getItems().add(creneau.toString());
                        }
                    });
            }
        } );
    }
    }



*/

    public void planifierTacheSimple(TacheSimple tache, LocalDateTime date, Creneau creneau) {
        int fois = tache.getPeriodicite().getFois();
        int jours = tache.getPeriodicite().getJours();
        System.out.println("rani nedkhoool");
        // Check if the calendrier is empty
        if (calendrier.getPlannings().isEmpty()) {
            // Create a new planning and add it to the calendrier
            Planning newPlanning = new Planning();
            calendrier.addPlanning(newPlanning);
        }
        LocalDateTime currentDate = date;
        System.out.println(currentDate);
        for (int k = 0; k < fois; k++) {
            // Increment the date for each iteration
            boolean trouv = false; // Reset trouv to false for each iteration
            for (int i = 0; i < calendrier.getPlannings().size() && !trouv; i++) {
                Planning planning = calendrier.getPlanning(i);

                // Check if the planning doesn't have any jours
                if (planning.getJours().isEmpty()) {
                    System.out.println("first");
                    // Create a new jour and add it to the planning
                    Jour newJour = new Jour(currentDate);
                    planning.ajouterJour(newJour);
                }

                Iterator<Jour> jourIterator = planning.getJours().iterator();
                while (jourIterator.hasNext() && !trouv) {
                    System.out.println("second");
                    Jour jour = jourIterator.next();
                    System.out.println("current date : "+currentDate);
                    System.out.println("jour : "+jour.getDate()+"kifkif?: "+jour.getDate().equals(currentDate));
                    if (jour.getDate().isEqual(currentDate)) {
                        System.out.println("rana dkhelna yoo"+jour.getDate());
                        if (jour.getCreneaux().isEmpty()) {
                            System.out.println("Il n'y a pas de creneaux");
                            trouv = true;
                        } else {
                            System.out.println("third");
                            boolean creneauFound = false;
                            for (Creneau creneau1 : jour.getCreneaux()) {
                                if (creneau1.getType().equals(creneau.getType())&&(creneau1.isBloque()==creneau.isBloque())) {
                                    if (creneau1.getType().equals("libre") && creneau1.getDuree() >= tache.getDuree()) {
                                        if (creneau1.getDuree() == tache.getDuree() ){
                                            creneau1.ajouterTache(tache);
                                        }
                                        else{

                                                try {
                                                    creneau1=creneau1.decomposer(creneau1.getDebut(),creneau1.getDebut()+tache.getDuree(), tache);
                                                    jour.ajouterCreneau(creneau1);
                                                } catch (DureeMinExeption e) {
                                                    throw new RuntimeException(e);
                                                }
                                        }
                                    }
                                    creneauFound = true;
                                    trouv = true;
                                    break;
                                }
                            }
                            if (!creneauFound) {
                                System.out.println("makach creneau kima hebitou!!");
                                trouv = true;
                            }
                        }
                    }
                }
            }
            currentDate=currentDate.plusDays(jours);
        }
    }



    public void planifierTacheSimpleAutomatiquement(TacheSimple tache) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime date = now.withHour(0).withMinute(0); // Set the date to today
        //trouver le premier creneau libre
        for (Planning planning : calendrier.getPlannings()) {
            for (Jour jour : planning.getJours()) {
                if (jour.getDate().isAfter(now) || jour.getDate().isEqual(now)) {

                    for (Creneau creneau : jour.getCreneaux()) {
                        if (creneau.getType().equals("libre")&& creneau.getDuree() >= tache.getDuree()) {
                            //plannifier la tache dans le premier creneau libre
                            Creneau cren = tache.planifier(creneau);
                            if(cren!=null) jour.ajouterCreneau(cren);
                            System.out.println("La tache " + tache.getNom() + " était automatiquement plannifier");
                            if(cren!=null) cren.afficherCreneau();
                            creneau.afficherCreneau();
                            System.out.println(jour.toString());
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("Il n'existe pas un creneau libre pour plannifier cette tache !");
    }

    public void planifierTacheDeco(TacheDecomposable tache,LocalDateTime date,ArrayList<Creneau> creneaux){
        ArrayList<TacheSimple> taches=tache.getTaches();
        int j=0;
        for(int i=0;i<taches.size();i++){
            this.planifierTacheSimple(taches.get(i), date,creneaux.get(j));
            j++;
        }
    }


    public Etat_realisation evaluer(ArrayList<Tache> taches) {
        Etat_realisation etat = null;
        int maxOccurrence = 0;

        for (int i = 0; i < taches.size(); i++) {
            int occurrence = 1;

            for (int j = i + 1; j < taches.size(); j++) {
                if (taches.get(i).equals(taches.get(j))) {
                    occurrence++;
                }
            }

            if (occurrence > maxOccurrence) {
                maxOccurrence = occurrence;
                etat = taches.get(i).getEtatRealisation();
            }
        }

        return etat;
    }



    public void afficher_etat_projet(String nomProjet){
        ArrayList<Tache> liste=new ArrayList<>();
        Etat_realisation etat=null;
        for (int i = 0; i < calendrier.getPlannings().size(); i++) {
            Planning planning = calendrier.getPlanning(i);
            Iterator<Jour> jourIterator = planning.getJours().iterator();
            while (jourIterator.hasNext() ) {
                Jour jour = jourIterator.next();
                Iterator<Creneau> creneauIterator = jour.getCreneaux().iterator();
                while (creneauIterator.hasNext()) {
                    Creneau creneau = creneauIterator.next();
                    String nom=creneau.getTache().getProjet().getNom();
                    if(nom.equals(nomProjet)){
                        liste.add(creneau.getTache());
                    }
                }
            }
        }
        etat= evaluer(liste);
        System.out.println("l'état du projet "+nomProjet+" est :"+etat);
    }


    public int rendement_jour(LocalDateTime date){
        date=LocalDateTime.now();
        int tachesTotale=0;
        int tachesCompletes = 0;

        for (int i = 0; i < calendrier.getPlannings().size(); i++) {
            Planning planning = calendrier.getPlanning(i);
            Jour jour = planning.getJour(date);
            if (jour != null) {
                tachesTotale=jour.getCreneaux().size();

                for (Creneau creneau : jour.getCreneaux()) {
                    Tache tache = creneau.getTache();
                    if (tache != null && tache.getEtatRealisation() == Etat_realisation.completed) {
                        tachesCompletes++;
                    }
                }
            }
        }

        int rend=tachesTotale-tachesCompletes;
        System.out.println("Le rendement du jour "+date+" égal à "+rend);

        return rend;
    }

    public double moyenneRendementJournalier() {
        Planning firstPlanning = calendrier.getPlanning(0);
        Jour firstJour = firstPlanning.getJour(0);
        LocalDateTime startDate = firstJour.getDate();
        LocalDateTime endDate = LocalDateTime.now();

        int totalRendement = 0;
        int numDays = 0;

        LocalDateTime date = startDate;
        while (date.isBefore(endDate) || date.isEqual(endDate)) {
            int rend = rendement_jour(date);
            totalRendement += rend;
            numDays++;

            date = date.plusDays(1);
        }

        double moyenneRendement = (double) totalRendement / numDays;
        return moyenneRendement;
    }

    public void setMinDureeCreneau(int minDureeCreneau) {
        this.minDureeCreneau = minDureeCreneau;
    }

    public void modiferDureeCreneau(int minDuree)
    {
        setMinDureeCreneau(minDuree);
        Creneau.setDureeMin(minDuree);
    }

    public void afficherCategories()
    {
        for(Categorie categorie : categories)
        {
            System.out.println("Categorie " +categorie.toString());
        }
    }

    public String getPseudo()
    {
        return pseudo;
    }

    public Set<Categorie> getCategories() {
        return categories;
    }
}

