package com.example.tp.Backend;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Calendrier implements Serializable
{
    private ArrayList<Planning> plannings = new ArrayList<Planning>();

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

    public void creerPlanning(Periode periode ,ArrayList<Jour> jours)
    {
        Planning planning = new Planning(jours,periode);
        plannings.add(planning);
    }

    public void addPlanning(Planning planning)
    {
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
          if(etat.equals(Etat_realisation.completed))
              nbComplete ++;
          if(etat.equals(Etat_realisation.cancelled))
              nbAnnule ++;
          if(etat.equals(Etat_realisation.in_progress))
              nbEnCour++;
          if(etat.equals(Etat_realisation.notRealized))
              nbNonRealise++;
          if(etat.equals(Etat_realisation.delayed))
              nbReporte++;
        }
        return Map.of(
                Etat_realisation.completed,nbComplete,
                Etat_realisation.cancelled,nbAnnule,
                Etat_realisation.in_progress,nbEnCour,
                Etat_realisation.notRealized,nbNonRealise,
                Etat_realisation.delayed,nbReporte
        );
    }
}

