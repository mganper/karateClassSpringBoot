package es.upo.tfg.manuelgandul.appkarate.entity.clase;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Alumno;

import java.io.Serializable;

public class CompositeKey implements Serializable {
    private int id;
    private Alumno alumno;
    private Clase clase;
}
