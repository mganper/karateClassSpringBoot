package es.upo.tfg.manuelgandul.appkarate.repository.clase;

import es.upo.tfg.manuelgandul.appkarate.entity.clase.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("claseJpaRepository")
public interface ClaseJpaRepository extends JpaRepository<Clase, Serializable> {
    public Clase findById(int id);
}
