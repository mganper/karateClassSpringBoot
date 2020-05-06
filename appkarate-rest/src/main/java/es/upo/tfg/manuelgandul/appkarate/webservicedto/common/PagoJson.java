package es.upo.tfg.manuelgandul.appkarate.webservicedto.common;

public class PagoJson {

    int id;

    int mes;

    int anyo;

    double cantidad;

    public PagoJson() {
    }

    public PagoJson(int id, int mes, int anyo, double cantidad) {
        this.id = id;
        this.mes = mes;
        this.anyo = anyo;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
