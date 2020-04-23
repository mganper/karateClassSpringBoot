package es.upo.tfg.manuelgandul.appkarate.model.clase;

import es.upo.tfg.manuelgandul.appkarate.model.Dto;
import es.upo.tfg.manuelgandul.appkarate.model.relations.AlumnoClaseDto;

import java.time.LocalDate;
import java.util.Objects;

public class FaltasDto extends Dto {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FaltasDto faltasDto = (FaltasDto) o;
        return fecha.equals(faltasDto.fecha) &&
                alumnoClase.equals(faltasDto.alumnoClase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, alumnoClase);
    }
}
