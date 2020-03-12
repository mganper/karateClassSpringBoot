package es.upo.tfg.manuelgandul.appkarate.repository.clase;

import es.upo.tfg.manuelgandul.appkarate.entity.centro.Centro;
import es.upo.tfg.manuelgandul.appkarate.entity.clase.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("claseJpaRepository")
public interface ClaseJpaRepository extends JpaRepository<Clase, Serializable> {
    Clase findById(int id);

    List<Clase> findAllByCentro(Centro centro);
}
