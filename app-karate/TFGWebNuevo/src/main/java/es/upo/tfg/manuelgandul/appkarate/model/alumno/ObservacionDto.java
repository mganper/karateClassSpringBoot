package es.upo.tfg.manuelgandul.appkarate.model.alumno;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ObservacionDto {

    private int id;

    private AlumnoDto alumno;

    private String fechaString;

    private int idAlumno;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha;

    private String dato;

    public ObservacionDto() {
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

    public String getFechaString() {
        return fechaString;
    }

    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public void fechaStringToLocalDate(){
        int anyo = Integer.parseInt(fechaString.substring(0,4));
        int mes = Integer.parseInt(fechaString.substring(5,7));
        int dia = Integer.parseInt(fechaString.substring(8));

        fecha = LocalDate.of(anyo, mes, dia);
    }
}
