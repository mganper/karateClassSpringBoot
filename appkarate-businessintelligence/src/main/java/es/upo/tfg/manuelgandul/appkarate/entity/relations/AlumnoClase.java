package es.upo.tfg.manuelgandul.appkarate.entity.relations;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Alumno;
import es.upo.tfg.manuelgandul.appkarate.entity.clase.Clase;
import es.upo.tfg.manuelgandul.appkarate.entity.clase.CompositeKey;

import javax.persistence.*;

@Entity
@Table(name = "alumnoclase")
public class AlumnoClase {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Alumno alumno;

    @ManyToOne
    private Clase clase;

    public AlumnoClase() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }
}
