package es.upo.tfg.manuelgandul.appkarate.entity.clase;

import es.upo.tfg.manuelgandul.appkarate.entity.relations.AlumnoClase;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "faltas")
public class Faltas {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne
    private AlumnoClase alumnoClase;

    public Faltas() {
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

    public AlumnoClase getAlumnoClase() {
        return alumnoClase;
    }

    public void setAlumnoClase(AlumnoClase alumnoClase) {
        this.alumnoClase = alumnoClase;
    }
}
