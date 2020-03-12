package es.upo.tfg.manuelgandul.appkarate.repository.alumno;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("alumnoJpaRepository")
public interface AlumnoJpaRepository extends JpaRepository<Alumno, Serializable> {
    Alumno findById(int id);
}
