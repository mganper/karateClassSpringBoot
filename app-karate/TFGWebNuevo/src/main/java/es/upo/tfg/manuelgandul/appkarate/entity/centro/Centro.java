package es.upo.tfg.manuelgandul.appkarate.entity.centro;

import javax.persistence.*;

@Entity
@Table(name = "centro")
public class Centro {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion", unique = true)
    private String direccion;

    @Column(name = "horma_maxima_inicio")
    private String hora_maxima_inicio;

    @Column(name = "hora_maxima_fin")
    private String hora_maxima_fin;

    @Column(name = "max_clases")
    private int max_clases;

    @Column(name = "precio_mes")
    private double precio_mes;

    @Column(name = "activo")
    private boolean activo;

    @OneToOne
    private Responsable responsable;

    public Centro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHora_maxima_inicio() {
        return hora_maxima_inicio;
    }

    public void setHora_maxima_inicio(String hora_maxima_inicio) {
        this.hora_maxima_inicio = hora_maxima_inicio;
    }

    public String getHora_maxima_fin() {
        return hora_maxima_fin;
    }

    public void setHora_maxima_fin(String hora_maxima_fin) {
        this.hora_maxima_fin = hora_maxima_fin;
    }

    public int getMax_clases() {
        return max_clases;
    }

    public void setMax_clases(int max_clases) {
        this.max_clases = max_clases;
    }

    public double getPrecio_mes() {
        return precio_mes;
    }

    public void setPrecio_mes(double precio_mes) {
        this.precio_mes = precio_mes;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }
}
