package es.upo.tfg.manuelgandul.appkarate.model.alumno;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Alumno;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

public class PagoDto {

    private int id;

    private AlumnoDto alumno;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha;

    @DateTimeFormat(pattern = "MM/yyyy")
    private LocalDate mesPagado;

    private double cantidad;

    public PagoDto() {
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

    public LocalDate getMesPagado() {
        return mesPagado;
    }

    public void setMesPagado(LocalDate mes_pagado) {
        this.mesPagado = mes_pagado;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagoDto pagoDto = (PagoDto) o;
        return id == pagoDto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
