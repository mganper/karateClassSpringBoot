package es.upo.tfg.manuelgandul.appkarate.repository.clase;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Alumno;
import es.upo.tfg.manuelgandul.appkarate.entity.clase.Clase;
import es.upo.tfg.manuelgandul.appkarate.entity.clase.Faltas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("faltasJpaRepository")
public interface FaltasJpaRepository extends JpaRepository<Faltas, Serializable> {
    List<Faltas> findAllByAlumnoClase_AlumnoOrderByFecha(Alumno alumno);

    List<Faltas> findAllByAlumnoClase_ClaseOrderByFecha(Clase clase);

    Faltas findById(int id);
}
