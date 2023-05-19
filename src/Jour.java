import java.time.LocalDateTime;
import java.util.*;
public class Jour 
{
    private LocalDateTime date;
    private int debutDuree;
    private int finDuree;
    private ArrayList<Creneau> creneaux = new ArrayList<Creneau>();

    public Jour(ArrayList<Creneau> creneaux) {
        this.creneaux = creneaux;
    }

    public LocalDateTime getDate() {
        return date;
    }
    
    public boolean equals(Jour jour) {
        if(date.equals(jour.getDate()))
            return true;
        return false;
    }

    public int getDebutDuree() {
        return debutDuree;
    }

    public int getFinDuree() {
        return finDuree;
    }

    public ArrayList<Creneau> getCreneaux() {
        return creneaux;
    }
 
    public void ajouterTache(Tache tache,Creneau creneau){  //exception 
        if((tache.getDateLimite()).compareTo(this.getDate())<0){
        Iterator<Creneau> it= creneaux.iterator();
        boolean trouv=false;
        while(it.hasNext()&&!trouv){
            Creneau cr = it.next();
            if(cr.equals(creneau)){
                trouv=true;
                cr.ajouterTache(tache);
            }
        }
if(trouv==false){
    System.out.println("ce creneau n'existe pas dans la liste de creneaux de ce jour");
}
        }
        else System.out.println("la date limite est superieur a la date de ce jour");

    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDebutDuree(int debutDuree) {
        this.debutDuree = debutDuree;
    }

    public void setFinDuree(int finDuree) {
        this.finDuree = finDuree;
    }

    public void setCreneaux(ArrayList<Creneau> creneaux) {
        this.creneaux = creneaux;
    }


}