import java.sql.Date;

public class Periode 
{
    private String dateDebut;
    private String dateFin;
    
    public String getDateDebut() 
    {
        return dateDebut;
    }

    public String getDateFin() 
    {
        return dateFin;
    }

    public boolean equals(Periode periode)
    {
        if((dateDebut.equals(periode.getDateDebut())) &&(dateFin.equals(periode.getDateFin())))
            return true;
        return false;
    }
}