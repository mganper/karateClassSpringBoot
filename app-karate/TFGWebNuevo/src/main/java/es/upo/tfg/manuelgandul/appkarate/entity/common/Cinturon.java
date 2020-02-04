package es.upo.tfg.manuelgandul.appkarate.entity.common;

import javax.persistence.*;

@Entity
@Table(name = "cinturon")
public class Cinturon {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nomnbre;

    public Cinturon() {
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
}
