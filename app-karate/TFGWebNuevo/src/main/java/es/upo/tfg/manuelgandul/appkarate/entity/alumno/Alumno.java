package es.upo.tfg.manuelgandul.appkarate.entity.alumno;

import es.upo.tfg.manuelgandul.appkarate.entity.common.Cinturon;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "alumno")
public class Alumno implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "dni")
    private String dni;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "sexo")
    private String sexo;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fecha_nacimiento")
    private LocalDate fecha_nacimiento;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono_contacto")
    private String telefono_contacto;

    @ManyToOne
    private Cinturon cinturon;

    @Column(name = "activo")
    private boolean activo;

    public Alumno() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono_contacto() {
        return telefono_contacto;
    }

    public void setTelefono_contacto(String telefono_contacto) {
        this.telefono_contacto = telefono_contacto;
    }

    public Cinturon getCinturon() {
        return cinturon;
    }

    public void setCinturon(Cinturon cinturon) {
        this.cinturon = cinturon;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
