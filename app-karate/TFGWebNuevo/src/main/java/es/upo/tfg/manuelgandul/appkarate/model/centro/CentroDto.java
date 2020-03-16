package es.upo.tfg.manuelgandul.appkarate.model.centro;

import java.util.Objects;

public class CentroDto {

    private int id;

    private String nombre;

    private String direccion;

    private String horaMaximaInicio;

    private String horaMaximaFin;

    private int maxClases;

    private double precioMes;

    private String activo;

    private ResponsableDto responsable;

    private int numClases;

    public CentroDto() {
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

    public String getHoraMaximaInicio() {
        return horaMaximaInicio;
    }

    public void setHoraMaximaInicio(String horaMaximaInicio) {
        this.horaMaximaInicio = horaMaximaInicio;
    }

    public String getHoraMaximaFin() {
        return horaMaximaFin;
    }

    public void setHoraMaximaFin(String horaMaximaFin) {
        this.horaMaximaFin = horaMaximaFin;
    }

    public int getMaxClases() {
        return maxClases;
    }

    public void setMaxClases(int maxClases) {
        this.maxClases = maxClases;
    }

    public double getPrecioMes() {
        return precioMes;
    }

    public void setPrecioMes(double precioMes) {
        this.precioMes = precioMes;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public ResponsableDto getResponsable() {
        return responsable;
    }

    public void setResponsable(ResponsableDto responsable) {
        this.responsable = responsable;
    }

    public int getNumClases() {
        return numClases;
    }

    public void setNumClases(int numClases) {
        this.numClases = numClases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CentroDto centroDto = (CentroDto) o;
        return id == centroDto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
