package es.upo.tfg.manuelgandul.appkarate.utility;

import es.upo.tfg.manuelgandul.appkarate.model.empleado.EmpleadoDto;
import es.upo.tfg.manuelgandul.appkarate.service.empleado.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

/**
 * Esta clase contiene métodos útiles para el resto de las clases.
 */
public class Utility {

    @Autowired
    @Qualifier("empleadoService")
    private static EmpleadoService empleadoService;

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
}
