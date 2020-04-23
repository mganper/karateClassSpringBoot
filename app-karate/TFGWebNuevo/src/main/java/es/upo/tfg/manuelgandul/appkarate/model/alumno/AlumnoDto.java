package es.upo.tfg.manuelgandul.appkarate.model.alumno;

import es.upo.tfg.manuelgandul.appkarate.model.Dto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;
import es.upo.tfg.manuelgandul.appkarate.model.common.CinturonDto;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class AlumnoDto extends Dto {

    private int id;

    @NotNull
    @Size(min = 9, max = 9)
    private String dni;

    @NotNull
    private String nombre;

    @NotNull
    private String apellidos;

    private String sexo;

    int edad;

//    @NotNull
    private String fechaString;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaNac;

    @Size(min = 9, max = 9)
    private String tlf;

    private String dir;

    private CinturonDto cint;

    private ClaseDto claseDto;

    private String activo;

    public AlumnoDto() {
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFechaString() {
        return fechaString;
    }

    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        if(fechaNac != null){
            this.edad = calculateAge(fechaNac);
            this.fechaNac = fechaNac;
        }
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

    public CinturonDto getCint() {
        return cint;
    }

    public void setCint(CinturonDto cint) {
        this.cint = cint;
    }

    public ClaseDto getClaseDto() {
        return claseDto;
    }

    public void setClaseDto(ClaseDto claseDto) {
        this.claseDto = claseDto;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlumnoDto alumnoDto = (AlumnoDto) o;
        return id == alumnoDto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private int calculateAge(LocalDate fechaNac){
        fechaNac = LocalDate.parse(fechaNac.toString());
        LocalDate ahora = LocalDate.now();
        ahora = LocalDate.parse(ahora.toString());

        return Period.between(fechaNac, ahora).getYears();
    }

}
