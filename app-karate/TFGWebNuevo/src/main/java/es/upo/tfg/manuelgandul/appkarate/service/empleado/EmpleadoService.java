package es.upo.tfg.manuelgandul.appkarate.service.empleado;


import es.upo.tfg.manuelgandul.appkarate.model.centro.CentroDto;
import es.upo.tfg.manuelgandul.appkarate.model.empleado.EmpleadoDto;

import java.util.List;

public interface EmpleadoService {

    public List<EmpleadoDto> listEmpleados();

    public List<EmpleadoDto> listProfesores();

    public EmpleadoDto addEmpleado(EmpleadoDto empleadoDto);

    public void removeEmpleado(EmpleadoDto empleadoDto);

    public EmpleadoDto updateEmpleado(EmpleadoDto empleadoDto);

    public EmpleadoDto getEmpleadoById(int id);

}
