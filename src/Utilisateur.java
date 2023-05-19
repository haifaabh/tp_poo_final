import javafx.scene.control.ListView;

import javax.script.ScriptEngine;
import java.time.LocalDateTime;
import java.util.*;;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
    String pseudo;
    private Calendrier calendrier;
    Badge badge;
    int nb_Tache_jours;
    int nb_Tache_Min;

    public Utilisateur(String pseudo, Calendrier calendrier) {
        this.pseudo = pseudo;
        this.calendrier = calendrier;
    }

    public Utilisateur() {

    }

    public void setNb_Tache_Min(int nb_Tache_Min) {
        this.nb_Tache_Min = nb_Tache_Min;
    }

    public void modifier_nb_min(int nb_min)
    {
     setNb_Tache_Min(nb_min);
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

    public void planifierTacheSimple(TacheSimple tache,LocalDateTime  date,Creneau creneau){
        int fois=tache.getPeriodicite().getFois();
        int jours=tache.getPeriodicite().getJours();
        boolean trouv = false;
        for(int k=0;k<fois;k++){ 
        for (int i = 0; i < calendrier.getPlannings().size() && !trouv; i++) {
            Planning planning = calendrier.getPlanning(i);
            Iterator<Jour> jourIterator = planning.getJours().iterator();
            while (jourIterator.hasNext() && !trouv) {
                Jour jour = jourIterator.next();
                if (jour.getDate().equals(date)){ 
                    trouv=true;
                   tache.planifier(creneau);
                }
                date=date.plusDays(jours);
            }}}
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


}
