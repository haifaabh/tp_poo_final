import java.util.*;
public class Projet {
 String nom;
 String description;
 
 public Projet(String nom, String description, ArrayList<Tache> taches) {
        this.nom = nom;
        this.description = description;
        this.taches = taches;
 }
 
 public Projet(String n,String d){
     this.nom=n;
     this.description=d;
 }

 public String getNom() {
    return nom;
}

public String getDescription() {
    return description;
}

public Etat_realisation evaluer( Tache taches[]) {
    Etat_realisation etat=null;
    int maxOccurence = 0;
         for (int i=0;i<taches.length;i++)
        {
         int occurrence=1;
         for(int j=i+1;j<taches.length;j++){
            if(taches[i].equals(taches[j])){
                occurrence++;
            } 
         }
         if(occurrence>maxOccurence){
            maxOccurence=occurrence;
            etat=taches[i].getEtatRealisation();
            }
        }  
        return etat;    
    }
}

