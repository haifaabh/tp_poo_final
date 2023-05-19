import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class Planning 
{
    private ArrayList<Jour> jours = new ArrayList<Jour>();
    private Periode periode;

    public Planning(ArrayList<Jour> jours) {
        this.jours = jours;
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

}