package es.upo.tfg.manuelgandul.appkarate.model.empleado;

import es.upo.tfg.manuelgandul.appkarate.model.common.CinturonDto;

import java.time.LocalDate;
import java.util.Objects;

public class EmpleadoDto {
    private int id;

    private String dni;

    private String nombre;

    private String apellidos;

    private String sexo;

    private LocalDate fechaNacimiento;

    private String fechaString;

    private String direccion;

    private String telefono;

    private double sueldo;

    private CinturonDto cinturon;

    private int gradoInstructor;

    private String cargo;

    private String tipoUsuario;

    private String activo;

    private String contrasenya;

    public EmpleadoDto() {
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaString() {
        return fechaString;
    }

    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
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

    public CinturonDto getCinturon() {
        return cinturon;
    }

    public void setCinturon(CinturonDto cinturon) {
        this.cinturon = cinturon;
    }

    public int getGradoInstructor() {
        return gradoInstructor;
    }

    public void setGradoInstructor(int gradoInstructor) {
        this.gradoInstructor = gradoInstructor;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpleadoDto that = (EmpleadoDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
