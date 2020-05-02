package es.upo.tfg.manuelgandul.appkarate.entity.clase;

import javax.persistence.*;

import es.upo.tfg.manuelgandul.appkarate.entity.centro.Centro;
import es.upo.tfg.manuelgandul.appkarate.entity.empleado.Empleado;

import java.io.Serializable;

@Entity
@Table(name = "clase")
public class Clase implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "hora_inicio")
    private String hora_inicio;

    @Column(name = "hora_fin")
    private String hora_fin;

    @Column(name = "edad_minima")
    private int edad_minima;

    @Column(name = "edad_maxima")
    private int edad_maxima;

    @Column(name = "max_alumnos")
    private int max_alumnos;

    @Column(name = "precio")
    private double precio;

    @Column(name = "activo")
    private boolean activo;

    @ManyToOne
    private Empleado profesor;

    @ManyToOne
    private Centro centro;

    public Clase() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public int getEdad_minima() {
        return edad_minima;
    }

    public void setEdad_minima(int edad_minima) {
        this.edad_minima = edad_minima;
    }

    public int getEdad_maxima() {
        return edad_maxima;
    }

    public void setEdad_maxima(int edad_maxima) {
        this.edad_maxima = edad_maxima;
    }

    public int getMax_alumnos() {
        return max_alumnos;
    }

    public void setMax_alumnos(int max_alumnos) {
        this.max_alumnos = max_alumnos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Empleado getProfesor() {
        return profesor;
    }

    public void setProfesor(Empleado profesor) {
        this.profesor = profesor;
    }

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }
}
