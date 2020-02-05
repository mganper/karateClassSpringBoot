package es.upo.tfg.manuelgandul.appkarate.converter.empleado;

import es.upo.tfg.manuelgandul.appkarate.converter.common.CinturonConverter;
import es.upo.tfg.manuelgandul.appkarate.entity.empleado.Empleado;
import es.upo.tfg.manuelgandul.appkarate.model.empleado.EmpleadoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("empleadoConverter")
public class EmpleadoConverter {

    @Autowired
    @Qualifier("cinturonConverter")
    private CinturonConverter cinturonConverter;

    public EmpleadoDto entity2model(Empleado empleado){
        EmpleadoDto empleadoDto = new EmpleadoDto();

        empleadoDto.setId(empleado.getId());
        empleadoDto.setDni(empleado.getDni());
        empleadoDto.setNombre(empleado.getNombre());
        empleadoDto.setApellidos(empleado.getApellidos());
        empleadoDto.setFechaNacimiento(empleado.getFecha_nacimiento());
        empleadoDto.setDireccion(empleado.getDireccion());
        empleadoDto.setSueldo(empleado.getSueldo());
        empleadoDto.setCinturon(cinturonConverter.entity2model(empleado.getCinturon()));
        empleadoDto.setGradoInstructor(empleado.getGrado_instructor());
        empleadoDto.setCargo(empleado.getCargo());
        empleadoDto.setTipoUsuario(empleado.isTipo_usuario());
        empleadoDto.setActivo(empleado.isActivo());
        empleadoDto.setContrasenya(empleado.getContrasenya());

        return empleadoDto;
    }

    public Empleado model2entity(EmpleadoDto empleadoDto){
        Empleado empleado = new Empleado();

        empleado.setId(empleadoDto.getId());
        empleado.setDni(empleadoDto.getDni());
        empleado.setNombre(empleadoDto.getNombre());
        empleado.setApellidos(empleadoDto.getApellidos());
        empleado.setFecha_nacimiento(empleadoDto.getFechaNacimiento());
        empleado.setDireccion(empleadoDto.getDireccion());
        empleado.setSueldo(empleadoDto.getSueldo());
        empleado.setCinturon(cinturonConverter.model2entity(empleadoDto.getCinturon()));
        empleado.setGrado_instructor(empleadoDto.getGradoInstructor());
        empleado.setCargo(empleadoDto.getCargo());
        empleado.setTipo_usuario(empleadoDto.isTipoUsuario());
        empleado.setActivo(empleadoDto.isActivo());
        empleado.setContrasenya(empleadoDto.getContrasenya());

        return empleado;
    }
}
