package com.example.tp.Backend;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Periode implements Serializable
{
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    public Periode(LocalDateTime debut,LocalDateTime fin) throws DateException, DatesErronesException {
        if(debut.isBefore(LocalDateTime.now())) throw new DateException();
        if(debut.isAfter(fin)) throw new DatesErronesException();
        dateDebut = debut;
        dateFin = fin;
    }

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

