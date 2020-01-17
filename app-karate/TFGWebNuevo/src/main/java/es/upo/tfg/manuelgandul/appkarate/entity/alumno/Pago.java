package es.upo.tfg.manuelgandul.appkarate.entity.alumno;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "alumno")
    private Alumno alumno;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fecha")
    private LocalDate fecha;

    @DateTimeFormat(pattern = "MM/yyyy")
    @Column(name = "mes_pagado")
    private LocalDate mes_pagado;

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
}
