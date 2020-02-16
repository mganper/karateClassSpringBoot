package es.upo.tfg.manuelgandul.appkarate.repository.centro;

import es.upo.tfg.manuelgandul.appkarate.entity.centro.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("responsableJpaRepository")
public interface ResponsableJpaRepository extends JpaRepository<Responsable, Serializable> {
    public Responsable findById(int id);
}
