package es.upo.tfg.manuelgandul.appkarate.model.alumno;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Alumno;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ObservacionDto {

    private int id;

    private Alumno alumno;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha;

    private String dato;

    public ObservacionDto() {
    }

    public ObservacionDto(int id, Alumno alumno, LocalDate fecha, String dato) {
        this.id = id;
        this.alumno = alumno;
        this.fecha = fecha;
        this.dato = dato;
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
