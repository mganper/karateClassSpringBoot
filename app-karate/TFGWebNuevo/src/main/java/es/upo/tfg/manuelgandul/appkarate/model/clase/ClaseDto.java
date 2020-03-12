package es.upo.tfg.manuelgandul.appkarate.model.clase;

import es.upo.tfg.manuelgandul.appkarate.model.centro.CentroDto;
import es.upo.tfg.manuelgandul.appkarate.model.empleado.EmpleadoDto;

import java.util.Objects;

public class ClaseDto {

    private int id;

    private String horaInicio;

    private String horaFin;

    private int edadMinima;

    private int edadMaxima;

    private int maxAlumnos;

    private double precio;

    private String activo;

    private EmpleadoDto profesor;

    private CentroDto centro;

    private int numAlumnos;

    public ClaseDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    public int getEdadMaxima() {
        return edadMaxima;
    }

    public void setEdadMaxima(int edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    public int getMaxAlumnos() {
        return maxAlumnos;
    }

    public void setMaxAlumnos(int maxAlumnos) {
        this.maxAlumnos = maxAlumnos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public EmpleadoDto getProfesor() {
        return profesor;
    }

    public void setProfesor(EmpleadoDto profesor) {
        this.profesor = profesor;
    }

    public CentroDto getCentro() {
        return centro;
    }

    public void setCentro(CentroDto centro) {
        this.centro = centro;
    }

    public int getNumAlumnos() {
        return numAlumnos;
    }

    public void setNumAlumnos(int numAlumnos) {
        this.numAlumnos = numAlumnos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClaseDto claseDto = (ClaseDto) o;
        return id == claseDto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
