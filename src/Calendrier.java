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
}
