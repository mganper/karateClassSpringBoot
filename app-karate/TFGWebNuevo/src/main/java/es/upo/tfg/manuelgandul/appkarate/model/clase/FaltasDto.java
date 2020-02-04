package es.upo.tfg.manuelgandul.appkarate.model.clase;

import es.upo.tfg.manuelgandul.appkarate.model.relations.AlumnoClaseDto;

import java.time.LocalDate;

public class FaltasDto {

    private int id;

    private LocalDate fecha;

    private AlumnoClaseDto alumnoClase;

    public FaltasDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public AlumnoClaseDto getAlumnoClase() {
        return alumnoClase;
    }

    public void setAlumnoClase(AlumnoClaseDto alumnoClase) {
        this.alumnoClase = alumnoClase;
    }
}
