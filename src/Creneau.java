import java.util.*;

public class Creneau {
    private static int dureeMin =30; 
    private String type;
    private int duree; 
    private boolean bloque;
    private Tache tache;
    private int debut;
    private int fin;

    public Creneau(Tache tache, int debut, int fin) {
        this.tache = tache;
        this.debut = debut;
        this.fin = fin;
    }
    
    public Creneau() {
        // Default constructor
    }
    //Les getters
    public String getType() 
    {
        return type;
    }
    
    public int getDebut() {
        return debut;
    }
    
    public int getFin() {
        return fin;
    }

    public int getDuree() 
    {
        return duree;
    }

    public boolean isBloque() 
    {
        return bloque;
    }

    public Tache getTache() 
    {
        return tache;
    }

    //Le constructeur
    public Creneau(int debut,int fin) 
    {
        duree = fin - debut;
        type = "libre";
    }

    public void ajouterTache(Tache tache)
    {
     this.tache=tache;
     type = "occupé";
    }

    public Creneau decomposer(int debut,int fin,Tache tache)
    {
        Creneau creneau = new Creneau(debut, fin);
        duree = duree - (fin - debut);
        creneau.ajouterTache(tache);
        return creneau;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Creneau)) {
            return false;
        }
        Creneau other = (Creneau) obj;
        return Objects.equals(type, other.type) &&
                duree == other.duree &&
                bloque == other.bloque &&
                Objects.equals(tache, other.tache) &&
                debut == other.debut &&
                fin == other.fin;
    }

    
    public void afficherCreneau() {
        System.out.println("Type: " + type);
        System.out.println("Duree: " + duree);
        System.out.println("Bloque: " + bloque);
        if (tache != null) {
            System.out.println("Tache: " + tache.getNom());
        } else {
            System.out.println("Tache: null");
        }
        System.out.println("Debut: " + debut);
        System.out.println("Fin: " + fin);
    }
    
    public Creneau decomposer(int duree,Tache tache)throws DureeMinExeption
    {
        Creneau creneau = new Creneau(this.duree-duree);
        if(creneau.getDuree()<dureeMin) throw new DureeMinExeption();
        this.duree = duree;
        ajouterTache(tache);
        return creneau;
    }
    
     public void suppTache()
    {
        tache = null;
        type = "libre";
    }

    public void rendreLibre()
    {
        type = "libre";
    } 
 }

