package es.upo.tfg.manuelgandul.appkarate.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

/**
 * Esta clase contiene métodos útiles para el resto de las clases.
 */
public class Utility {

    /**
     * Devuelve un objeto LocalDate a partir del String que recibe por parámetro.
     *
     * @param fechaString String con el formato de fecha YYYY-MM-DD.
     * @return Objeto LocalDate.
     */
    public static LocalDate stringToDate(String fechaString) {
        return LocalDate.parse(fechaString);
    }

    /**
     * Retorna la contraseña de ese usuario codificada.
     *
     * @param password contraseña en String que devolveremos docificada.
     * @return la clave codificada.
     */
    public static String passwordEncoder(String password) {
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        return pe.encode(password);
    }

    /**
     * Método que se encarga de comprobar si la contraseña concuerda con la contraseña codificada.
     *
     * @param password Contraseña sin codificar.
     * @param encodedPassword Contraseña codificada.
     * @return true si concuerda.
     */
    public static boolean matchPassword(String password, String encodedPassword){
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        return pe.matches(password, encodedPassword);
    }
}
