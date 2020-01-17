package es.upo.tfg.manuelgandul.appkarate.repository.alumno;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Observacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("observacionJpaRepository")
public interface ObservacionJpaRepository extends JpaRepository<Observacion, Serializable> {
    public List<Observacion> findAllByAlumno_IdOrderByFecha(int id);
}
