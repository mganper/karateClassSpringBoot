package es.upo.tfg.manuelgandul.appkarate.repository.centro;

import es.upo.tfg.manuelgandul.appkarate.entity.centro.Centro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("centroJpaRepository")
public interface CentroJpaRepository extends JpaRepository<Centro, Serializable> {

    public Centro findById(int id);
}
