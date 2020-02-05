package es.upo.tfg.manuelgandul.appkarate.repository.common;

import es.upo.tfg.manuelgandul.appkarate.entity.common.Cinturon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("cinturonJpaRepository")
public interface CinturonJpaRepository extends JpaRepository<Cinturon, Serializable> {
}
