import java.time.LocalDateTime;

public class Periode
{
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    public LocalDateTime getDateDebut()
    {
        return dateDebut;
    }

    public LocalDateTime getDateFin()
    {
        return dateFin;
    }

    public boolean equals(Periode periode)
    {
        if((dateDebut.equals(periode.getDateDebut())) &&(dateFin.equals(periode.getDateFin())))
            return true;
        return false;
    }

    public boolean inclus(LocalDateTime date)
    {
        if((!date.isAfter(dateFin))&&(!date.isBefore(dateDebut)))
        {
            return true;
        }
        return false;
    }
}
