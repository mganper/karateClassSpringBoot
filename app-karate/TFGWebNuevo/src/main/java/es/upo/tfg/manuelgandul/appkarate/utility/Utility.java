package es.upo.tfg.manuelgandul.appkarate.utility;

import java.time.LocalDate;

/**
 * Esta clase contiene métodos útiles para el resto de las clases.
 */
public class Utility {

    /**
     * Devuelve un objeto LocalDate a partir del String que recibe por parámetro.
     * @param fechaString String con el formato de fecha YYYY-MM-DD.
     * @return Objeto LocalDate.
     */
    public static LocalDate stringToDate(String fechaString){
        int anyo = Integer.parseInt(fechaString.substring(0,4));
        int mes = Integer.parseInt(fechaString.substring(5,7));
        int dia = Integer.parseInt(fechaString.substring(8));

        return LocalDate.of(anyo, mes, dia);
    }
}
