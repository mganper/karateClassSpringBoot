package es.upo.tfg.manuelgandul.appkarate.repository.relations;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("alumnoClaseJpaRepository")
public interface AlumnoClaseJpaRepository extends JpaRepository<Alumno, Serializable> {
}
