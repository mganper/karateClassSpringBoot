package es.upo.tfg.manuelgandul.appkarate.entity.alumno;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "observacion")
public class Observacion {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    private Alumno alumno;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "dato")
    private String dato;

    public Observacion() {
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }
}
