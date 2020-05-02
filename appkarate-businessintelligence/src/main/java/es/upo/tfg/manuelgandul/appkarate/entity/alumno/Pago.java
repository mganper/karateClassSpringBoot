package es.upo.tfg.manuelgandul.appkarate.entity.alumno;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    private Alumno alumno;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fecha")
    private LocalDate fecha;

    @DateTimeFormat(pattern = "MM/yyyy")
    @Column(name = "mes_pagado")
    private LocalDate mes_pagado;

    @Column(name="cantidad")
    private double cantidad;

    public Pago() {
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

    public LocalDate getMes_pagado() {
        return mes_pagado;
    }

    public void setMes_pagado(LocalDate mes_pagado) {
        this.mes_pagado = mes_pagado;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
