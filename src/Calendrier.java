import java.util.ArrayList;
import java.util.Iterator;

public class Calendrier 
{
    private ArrayList<Planning> plannings = new ArrayList<Planning>();

    public Calendrier(ArrayList<Planning> plannings) {
        this.plannings = plannings;
    }

  /*  public void creerPlanning(Periode periode , ArrayList<Jour> jours)
    {
        Planning planning = new Planning(jours,periode);
        plannings.add(planning);
    }*/

    
    public void addPlanning(Planning planning) {
    plannings.add(planning);
    }
    
    public Planning getPlanning(Planning planning)
    {
        if(plannings.contains(planning))
        {
            int i=0;
            Iterator<Planning> it = plannings.iterator();
            while(it.hasNext())
            {
                Planning plan = it.next();
                if(plan.equals(planning))
                {
                    i = plannings.indexOf(plan);
                    
                }
            }
            return plannings.get(i);
        }
        else
        {
            System.out.println("Il n'y a pas de planning de ce genre!");
            return null;
        }
        
    }

    public Planning getPlanning(int i) {
        if (i >= 0 && i < plannings.size()) {
            return plannings.get(i);
        } else {
            System.out.println("indice n'existe pas!");
            return null;
        }
    }

    public void setPlannings(ArrayList<Planning> plannings) {
        this.plannings = plannings;
    }

    public ArrayList<Planning> getPlannings() {
        return plannings;
    }
    
    public void reporter(Tache tache, LocalDateTime date,Planning planning)
    {
        planning.suppTache(tache);
        boolean stop=false;
        Iterator<Planning> it = plannings.iterator();
        while((it.hasNext())&&(!stop))
        {
            Planning plan = it.next();
            if(plan.getPeriode().inclus(date))
            {
                plan.ajouterTache(tache, date);
            }
        }
    }

    public void miseAJourPlanning(Planning planning)
    {
        planning.miseAJour();
    }

    public Map<Etat_realisation, Integer> getEtats(LocalDateTime date)
    {
        int nbComplete = 0, nbNonRealise =0, nbEnCour=0 , nbAnnule=0 , nbReporte=0 ;
        Jour jour = new Jour(date);
        boolean stop=false;
        Iterator<Planning> it = plannings.iterator();
        while((it.hasNext())&&(!stop))
        {
            Planning plan = it.next();
            if(plan.getPeriode().inclus(date))
            {
                stop = true;
                jour = plan.getJour(date);
            }
        }
        ArrayList<Creneau> creneaux = jour.getCreneaux();
        for(Creneau creneau : creneaux )
        {
          Etat_realisation etat = creneau.getTache().getEtatRealisation();
          if(etat.equals(Etat_realisation.Complétée))
              nbComplete ++;
          if(etat.equals(Etat_realisation.Annulée))
              nbAnnule ++;
          if(etat.equals(Etat_realisation.En_Cours))
              nbEnCour++;
          if(etat.equals(Etat_realisation.Non_Realisée))
              nbNonRealise++;
          if(etat.equals(Etat_realisation.Reportée))
              nbReporte++;
        }
        return Map.of(
                Etat_realisation.Complétée,nbComplete,
                Etat_realisation.Annulée,nbAnnule,
                Etat_realisation.En_Cours,nbEnCour,
                Etat_realisation.Non_Realisée,nbNonRealise,
                Etat_realisation.Reportée,nbReporte
        );
    }
}
