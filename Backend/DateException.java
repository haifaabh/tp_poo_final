package com.example.tp.Backend;

import java.lang.reflect.Executable;

public class DateException extends Exception {
    public DateException(){
        System.out.println("Il faux choisir une date début de la période ultérieure a la date du jour!!");
    }
}
