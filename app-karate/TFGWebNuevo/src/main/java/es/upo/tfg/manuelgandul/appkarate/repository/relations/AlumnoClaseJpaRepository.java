package es.upo.tfg.manuelgandul.appkarate.repository.relations;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Alumno;
import es.upo.tfg.manuelgandul.appkarate.entity.clase.Clase;
import es.upo.tfg.manuelgandul.appkarate.entity.relations.AlumnoClase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("alumnoClaseJpaRepository")
public interface AlumnoClaseJpaRepository extends JpaRepository<AlumnoClase, Long> {

    void removeByAlumno(Alumno alumno);

    AlumnoClase findByAlumno_Id(int id);

    List<AlumnoClase> findAllByClase(Clase clase);

    void removeAllByClase(Clase clase);

    <T> T findById(Long id, Class<T> classType);
}