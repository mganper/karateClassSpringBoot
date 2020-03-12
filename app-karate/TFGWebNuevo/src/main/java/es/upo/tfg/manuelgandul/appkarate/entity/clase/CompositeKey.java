package es.upo.tfg.manuelgandul.appkarate.entity.clase;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Alumno;

import java.io.Serializable;
import java.util.Objects;

public class CompositeKey implements Serializable {
    private Alumno alumno;
    private Clase clase;

    public CompositeKey() {
    }

    public CompositeKey(Alumno alumno, Clase clase) {
        this.alumno = alumno;
        this.clase = clase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeKey that = (CompositeKey) o;
        return Objects.equals(alumno, that.alumno) &&
                Objects.equals(clase, that.clase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alumno, clase);
    }
}
