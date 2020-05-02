package es.upo.tfg.manuelgandul.appkarate.entity.empleado;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tipo_usuario"/*, uniqueConstraints = @UniqueConstraint(columnNames = {"tipo", "empleado"})*/)
public class TipoUsuario {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Empleado> empleado = new HashSet<>();

    @Column(name = "tipo")
    private String tipo;

    public TipoUsuario() {
    }

    public TipoUsuario(Set<Empleado> empleado, String tipo) {
        this.empleado = empleado;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Empleado> getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Set<Empleado> empleado) {
        this.empleado = empleado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
