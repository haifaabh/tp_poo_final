import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;

public class Planning 
{
    private ArrayList<Jour> jours = new ArrayList<Jour>();
    private Periode periode;

    public Planning(ArrayList<Jour> jours) {
        this.jours = jours;
    }
    
    public Planning(ArrayList<Jour> jours, Periode periode)
    {
        this.jours = jours;
        this.periode = periode;
    }
    
    public Jour getJour(int i) {
        if (i >= 0 && i < jours.size()) {
            return jours.get(i);
        } else {
            System.out.println("indice n'existe pas!");
            return null;
        }
    }

    public Periode getPeriode() 
    {
        return periode;
    }
    
    public boolean equals(Planning planning)
    {
        if((jours.equals(planning.getJours())) && (periode.equals(planning.getPeriode())))
        {
                return true;
        }
        return false;
    }

    // public Tache get(Tache tache)
    // {
    //     boolean trouv = false;
    //     int i=0;
    //     Iterator<Jour> it = jours.iterator();
    //     while(it.hasNext()&& !trouv)
    //     {
    //         Jour jour = it.next();
    //         if(jour.getType().equals("occupé"))
    //         {
    //             Tache tache2 = creneau.getTache();
    //             if(tache2.equals(tache))
    //             {
    //                 i = creneaux.indexOf(creneau);
    //                 trouv = true;
    //             }
    //         }   
    //     }
    //     if(trouv)
    //         return creneaux.get(i).getTache();
    //     else
    //     {
    //         System.out.println("Il n'y a pas de planning de ce genre!");
    //         return null;
    //     }
    // }




    public ArrayList<Jour> getJours() {
        return jours;
    }

    public void setJours(ArrayList<Jour> jours) {
        this.jours = jours;
    }
    public void setPeriode(Periode periode) {
        this.periode = periode;
    }

    public void ajouterJour(Jour jour) {
        jours.add(jour);
    }

    public Jour getJour(LocalDateTime date) {
        Iterator<Jour> jourIterator = this.getJours().iterator();
        while (jourIterator.hasNext() ) {
            Jour jour = jourIterator.next();
            if((jour.getDate()).equals(date)){
                return jour;
            }
    }
        return null;
}

    public void suppTache(Tache tache)
    {
        Iterator<Jour> it = jours.iterator();
        while(it.hasNext())
        {
            ArrayList<Creneau> creneaux = it.next().getCreneaux();
            for(Creneau creneau : creneaux)
            {
                if(creneau.getType().equals("occupé"))
                {
                    Tache tache2 = creneau.getTache();
                    if(tache2.equals(tache))
                    {
                        creneau.suppTache();
                    }
                }
            }
        }
    }

    public void ajouterTache(Tache tache , LocalDateTime date)
    {
        Iterator<Jour> it = jours.iterator();
        boolean stop = false;
        while(it.hasNext()&& !stop)
        {
            if(it.next().getDate().equals(date))
            {
                ArrayList<Creneau> creneaux = it.next().getCreneaux();
                for(Creneau creneau : creneaux)
                {
                    if(creneau.getType().equals("libre") && !stop)
                    {
                        if(creneau.getDuree()==tache.getDuree())
                            creneau.ajouterTache(tache);
                        else
                        {
                            try
                            {
                                Creneau creneau1= creneau.decomposer(tache.getDuree(), tache);
                                it.next().ajouterCreneau(creneau1);
                            }
                            catch (DureeMinExeption e)
                            {
                                e.printStackTrace();
                            }

                        }
                        stop=true;
                    }
                }
                if(!stop)
                {
                    Creneau creneau =  new Creneau(tache.getDuree());
                    creneau.ajouterTache(tache);
                    it.next().ajouterCreneau(creneau);
                }
            }
        }
    }

    public void miseAJour()
    {
        ArrayList<Tache> taches = new ArrayList<Tache>(); //Phase 1 : récuperer toutes les taches contenus dans les creneaux du planning , sauf ceux des creneaux bloques
        Iterator<Jour> it = jours.iterator();
        while(it.hasNext())
        {
            ArrayList<Creneau> creneaux = it.next().getCreneaux();
            for(Creneau creneau : creneaux)
            {
                if(!creneau.isBloque()&&creneau.getType().equals("occupé"))
                {
                    taches.add(creneau.getTache());
                    creneau.setType("libre");
                }
            }
        }
        //Phase 2 : ordonner les taches selon la priorité
        taches.sort(new Comparator<Tache>() {
            public int compare(Tache tache1,Tache tache2)
            {
                if(tache1.getPriorite()<tache2.getPriorite())
                    return -1;
                if(tache1.getPriorite()>tache2.getPriorite())
                    return 1;
                return 0;
            }
        });
        //Phase 3 : remplir les taches dans le planning
        it = jours.iterator();
        int i =0;
        Tache tache = taches.get(i);
        while(it.hasNext())
        {
            ArrayList<Creneau> creneaux = it.next().getCreneaux();
            Iterator<Creneau> cren = creneaux.iterator();
            Creneau creneau = cren.next();
            while(it.hasNext()&&i<taches.size())
            {
                if(creneau.getType().equals("libre"))
                {
                    if(creneau.getDuree()==tache.getDuree())
                    {
                        creneau.ajouterTache(tache);
                        creneau = cren.next();
                        i++;
                    }
                    else
                    {
                        if(creneau.getDuree()<tache.getDuree())
                            creneau = cren.next();
                        else
                        {
                            try
                            {
                                creneau= creneau.decomposer(tache.getDuree(), tache);
                                i++;
                            }
                            catch (DureeMinExeption e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
    
}
