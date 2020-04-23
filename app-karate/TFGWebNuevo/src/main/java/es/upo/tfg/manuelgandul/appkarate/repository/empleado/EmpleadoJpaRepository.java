package es.upo.tfg.manuelgandul.appkarate.repository.empleado;

import es.upo.tfg.manuelgandul.appkarate.entity.empleado.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("empleadoJpaRepository")
public interface EmpleadoJpaRepository extends JpaRepository<Empleado, Serializable> {
    Empleado findById(int id);

    Empleado findByDni(String dni);

    Empleado findByDniAndToken(String dni, String token);

    Empleado findByDniAndContrasenya(String dni, String contrasenya);
}
