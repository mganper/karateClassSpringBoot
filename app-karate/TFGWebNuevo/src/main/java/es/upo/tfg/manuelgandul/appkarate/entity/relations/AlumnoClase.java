package es.upo.tfg.manuelgandul.appkarate.entity.relations;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Alumno;
import es.upo.tfg.manuelgandul.appkarate.entity.clase.Clase;
import es.upo.tfg.manuelgandul.appkarate.entity.clase.CompositeKey;

import javax.persistence.*;

@Entity
//@IdClass(CompositeKey.class)
@Table(name = "alumnoclase")
public class AlumnoClase {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Alumno alumno;

    @ManyToOne
    private Clase clase;

    public AlumnoClase() {
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
