package es.upo.tfg.manuelgandul.appkarate.entity.clase;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Alumno;

import javax.persistence.*;

@Entity
@IdClass(CompositeKey.class)
@Table(name = "alumnoclase")
public class AlumnoClase {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Id
    @ManyToOne
    private Alumno alumno;

    @Id
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
