import java.lang.reflect.Executable;

public class DateException extends Exception {
 public DateException(){
  System.out.println("Il faux choisir une date début de la période ultérieur a la date du jours");
 }   
}
