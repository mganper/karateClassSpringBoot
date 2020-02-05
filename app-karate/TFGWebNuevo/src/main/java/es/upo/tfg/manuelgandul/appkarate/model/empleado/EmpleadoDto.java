package es.upo.tfg.manuelgandul.appkarate.model.empleado;

import es.upo.tfg.manuelgandul.appkarate.model.common.CinturonDto;

import java.time.LocalDate;

public class EmpleadoDto {
    private int id;

    private String dni;

    private String nombre;

    private String apellidos;

    private LocalDate fechaNacimiento;

    private String direccion;

    private double sueldo;

    private CinturonDto cinturon;

    private int gradoInstructor;

    private String cargo;

    private boolean tipoUsuario;

    private boolean activo;

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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public boolean isTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(boolean tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
}
