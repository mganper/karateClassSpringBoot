package es.upo.tfg.manuelgandul.appkarate.entity.empleado;

import es.upo.tfg.manuelgandul.appkarate.entity.common.Cinturon;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "empleado", uniqueConstraints = @UniqueConstraint(columnNames = {"dni"}))
public class Empleado {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "dni", unique = true, nullable = false, length = 9)
    private String dni;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "fecha_nacimiento")
    private LocalDate fecha_nacimiento;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono", unique = true, length = 9)
    private String telefono;

    @Column(name = "sueldo")
    private double sueldo;

    @ManyToOne
    private Cinturon cinturon;

    @Column(name = "grado_instructor")
    private int grado_instructor;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "profesor")
    private boolean profesor;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<TipoUsuario> tipo_usuario = new HashSet<>();

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @Column(name = "contrasenya", nullable = false, length = 60)
    private String contrasenya;

    @Column(name = "token")
    private String token;

    public Empleado() {
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

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public Cinturon getCinturon() {
        return cinturon;
    }

    public void setCinturon(Cinturon cinturon) {
        this.cinturon = cinturon;
    }

    public int getGrado_instructor() {
        return grado_instructor;
    }

    public void setGrado_instructor(int grado_instructor) {
        this.grado_instructor = grado_instructor;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean isProfesor() {
        return profesor;
    }

    public void setProfesor(boolean empleado) {
        this.profesor = empleado;
    }

    public Set<TipoUsuario> getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(Set<TipoUsuario> tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
