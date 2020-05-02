package es.upo.tfg.manuelgandul.appkarate.service.empleado;


import es.upo.tfg.manuelgandul.appkarate.model.centro.CentroDto;
import es.upo.tfg.manuelgandul.appkarate.model.empleado.EmpleadoDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface EmpleadoService {

    List<EmpleadoDto> listEmpleados();

    List<EmpleadoDto> listProfesores();

    EmpleadoDto addEmpleado(EmpleadoDto empleadoDto);

    void removeEmpleado(EmpleadoDto empleadoDto);

    EmpleadoDto updateEmpleado(EmpleadoDto empleadoDto);

    EmpleadoDto getEmpleadoById(int id);

    EmpleadoDto getEmpleadoByDni(String dni);

    EmpleadoDto getUserAuthenticated();

    EmpleadoDto loginRest(String user, String password);
}
