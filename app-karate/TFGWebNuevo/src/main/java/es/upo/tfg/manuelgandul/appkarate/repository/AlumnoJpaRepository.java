package es.upo.tfg.manuelgandul.appkarate.repository;

import es.upo.tfg.manuelgandul.appkarate.entity.Alumnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("alumnoJpaRepository")
public interface AlumnoJpaRepository extends JpaRepository<Alumnos, Serializable> {
}
