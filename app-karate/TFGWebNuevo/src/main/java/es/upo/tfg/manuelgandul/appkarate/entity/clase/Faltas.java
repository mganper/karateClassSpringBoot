package es.upo.tfg.manuelgandul.appkarate.entity.clase;

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
}
