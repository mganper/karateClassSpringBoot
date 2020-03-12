package es.upo.tfg.manuelgandul.appkarate.model.relations;

import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;

import java.util.Objects;

public class AlumnoClaseDto {

    private int id;

    private AlumnoDto alumno;

    private ClaseDto clase;

    public AlumnoClaseDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AlumnoDto getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoDto alumno) {
        this.alumno = alumno;
    }

    public ClaseDto getClase() {
        return clase;
    }

    public void setClase(ClaseDto clase) {
        this.clase = clase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlumnoClaseDto that = (AlumnoClaseDto) o;
        return alumno.equals(that.alumno) &&
                clase.equals(that.clase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alumno, clase);
    }
}
