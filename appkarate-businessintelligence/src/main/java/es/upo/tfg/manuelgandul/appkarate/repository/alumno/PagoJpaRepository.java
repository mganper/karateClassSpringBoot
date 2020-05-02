package es.upo.tfg.manuelgandul.appkarate.repository.alumno;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("pagoJpaRepository")
public interface PagoJpaRepository extends JpaRepository<Pago, Serializable> {
    public List<Pago> findAllByAlumno_IdOrderByFecha(int id);
    public Pago findById(int id);
}
