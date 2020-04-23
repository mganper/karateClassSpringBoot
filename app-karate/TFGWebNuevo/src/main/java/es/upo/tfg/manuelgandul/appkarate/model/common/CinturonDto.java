package es.upo.tfg.manuelgandul.appkarate.model.common;


import es.upo.tfg.manuelgandul.appkarate.model.Dto;

import java.util.Objects;

public class CinturonDto extends Dto {

    private int id;

    private String nomnbre;

    public CinturonDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomnbre() {
        return nomnbre;
    }

    public void setNomnbre(String nomnbre) {
        this.nomnbre = nomnbre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinturonDto that = (CinturonDto) o;
        return id == that.id &&
                nomnbre.equals(that.nomnbre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomnbre);
    }
}
