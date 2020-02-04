package es.upo.tfg.manuelgandul.appkarate.model.centro;

public class ResponsableDto {

    private int id;

    private String nombre;

    private String apellidos;

    private String cargo;

    private String correo;

    private String telefono;

    private CentroDto centro;

    public ResponsableDto() {
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public CentroDto getCentro() {
        return centro;
    }

    public void setCentro(CentroDto centro) {
        this.centro = centro;
    }
}
