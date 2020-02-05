package es.upo.tfg.manuelgandul.appkarate.repository.clase;

import es.upo.tfg.manuelgandul.appkarate.entity.clase.Faltas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("faltasJpaRepository")
public interface FaltasJpaRepository extends JpaRepository<Faltas, Serializable> {
}
