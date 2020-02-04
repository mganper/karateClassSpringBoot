package es.upo.tfg.manuelgandul.appkarate.model.alumno;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Alumno;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PagoDto {

    private int id;

    private Alumno alumno;

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
}
