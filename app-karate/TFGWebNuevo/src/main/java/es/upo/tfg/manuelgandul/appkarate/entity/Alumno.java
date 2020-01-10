package es.upo.tfg.manuelgandul.appkarate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Table(name = "alumno")
public class Alumno {

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

//    @Column(name = "sexo")
//    private boolean sexo;

//    @Column(name = "fecha_nacimiento")
//    private Date fecha_nacimiento;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono_contacto")
    private String telefono_contacto;

//    @Column(name = "cinturon")
//    private String cinturon;

//    @Column(name = "activo")
//    private boolean activo;

    public Alumno() {
    }

    public Alumno(int id, String dni, String nombre, String apellidos, String direccion, String telefono_contacto){
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono_contacto = telefono_contacto;
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

//    public boolean isSexo() {
//        return sexo;
//    }
//
//    public void setSexo(boolean sexo) {
//        this.sexo = sexo;
//    }
//
//    public Date getFecha_nacimiento() {
//        return fecha_nacimiento;
//    }
//
//    public void setFecha_nacimiento(Date fecha_nacimiento) {
//        this.fecha_nacimiento = fecha_nacimiento;
//    }

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

//    public String getCinturon() {
//        return cinturon;
//    }
//
//    public void setCinturon(String cinturon) {
//        this.cinturon = cinturon;
//    }
//
//    public boolean isActivo() {
//        return activo;
//    }
//
//    public void setActivo(boolean activo) {
//        this.activo = activo;
//    }
}
