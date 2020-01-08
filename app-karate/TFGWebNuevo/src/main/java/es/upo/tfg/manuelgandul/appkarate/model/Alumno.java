package es.upo.tfg.manuelgandul.appkarate.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Alumno {

    @NotNull
    @Size(min = 9, max = 9)
    private String dni;

    @NotNull
    private String nombre;

    @NotNull
    private String apellidos;

    private boolean sexo;

    private Date fechaNac;

    @Size(min = 9, max = 9)
    private String tlf;

    private String dir;

    private String cint;

    public Alumno() {
    }

    public Alumno(String dni, String nombre, String apellidos, boolean sexo, Date fechaNac, String tlf, String dir, String cint) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.fechaNac = fechaNac;
        this.tlf = tlf;
        this.dir = dir;
        this.cint = cint;
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

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getCint() {
        return cint;
    }

    public void setCint(String cint) {
        this.cint = cint;
    }
}
