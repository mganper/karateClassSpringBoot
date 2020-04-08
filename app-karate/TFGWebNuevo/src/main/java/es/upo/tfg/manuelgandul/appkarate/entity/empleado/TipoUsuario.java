package es.upo.tfg.manuelgandul.appkarate.entity.empleado;

import javax.persistence.*;

@Entity
@Table(name = "tipo_usuario"/*, uniqueConstraints = @UniqueConstraint(columnNames = {"tipo", "empleado"})*/)
public class TipoUsuario {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Empleado empleado;

    @Column(name = "tipo")
    private String tipo;

    public TipoUsuario() {
    }

    public TipoUsuario(Empleado empleado, String tipo){
        this.empleado = empleado;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
