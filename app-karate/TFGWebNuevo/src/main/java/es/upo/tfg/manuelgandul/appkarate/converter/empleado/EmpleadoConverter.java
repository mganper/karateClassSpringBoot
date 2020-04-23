package es.upo.tfg.manuelgandul.appkarate.converter.empleado;

import es.upo.tfg.manuelgandul.appkarate.converter.common.CinturonConverter;
import es.upo.tfg.manuelgandul.appkarate.entity.empleado.Empleado;
import es.upo.tfg.manuelgandul.appkarate.model.empleado.EmpleadoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("empleadoConverter")
public class EmpleadoConverter {

    @Autowired
    @Qualifier("cinturonConverter")
    private CinturonConverter cinturonConverter;

    public EmpleadoDto entity2model(Empleado empleado) {
        EmpleadoDto empleadoDto = new EmpleadoDto();

        empleadoDto.setId(empleado.getId());
        empleadoDto.setDni(empleado.getDni());
        empleadoDto.setNombre(empleado.getNombre());
        empleadoDto.setApellidos(empleado.getApellidos());
        empleadoDto.setFechaNacimiento(empleado.getFecha_nacimiento());
        empleadoDto.setSexo(empleado.getSexo());
        empleadoDto.setDireccion(empleado.getDireccion());
        empleadoDto.setTelefono(empleado.getTelefono());
        empleadoDto.setSueldo(empleado.getSueldo());
        empleadoDto.setGradoInstructor(empleado.getGrado_instructor());
        empleadoDto.setCargo(empleado.getCargo());
        empleadoDto.setContrasenya(empleado.getContrasenya());
        empleadoDto.setToken(empleado.getToken());

        if (empleado.getCinturon() != null)
            empleadoDto.setCinturon(cinturonConverter.entity2model(empleado.getCinturon()));

        empleadoDto.setActivo((empleado.isActivo()) ? "Activo" : "Inactivo");
        empleadoDto.setTipoUsuario((empleado.isProfesor()) ? "Empleado" : "Profesor");

        return empleadoDto;
    }

    public Empleado model2entity(EmpleadoDto empleadoDto) {
        Empleado empleado = new Empleado();
        LocalDate date = empleadoDto.getFechaNacimiento().plusDays(1);

        empleado.setId(empleadoDto.getId());
        empleado.setDni(empleadoDto.getDni());
        empleado.setNombre(empleadoDto.getNombre());
        empleado.setApellidos(empleadoDto.getApellidos());
        empleado.setFecha_nacimiento(date);
        empleado.setSexo(empleadoDto.getSexo());
        empleado.setDireccion(empleadoDto.getDireccion());
        empleado.setTelefono(empleadoDto.getTelefono());
        empleado.setSueldo(empleadoDto.getSueldo());
        empleado.setGrado_instructor(empleadoDto.getGradoInstructor());
        empleado.setCargo(empleadoDto.getCargo());
        empleado.setContrasenya(empleadoDto.getContrasenya());
        empleado.setToken(empleadoDto.getToken());

        if (empleadoDto.getCinturon() != null)
            empleado.setCinturon(cinturonConverter.model2entity(empleadoDto.getCinturon()));

        empleado.setActivo((empleadoDto.getActivo() == null || empleadoDto.getActivo().equals("Activo")) ? true : false);
        empleado.setProfesor((empleadoDto.getTipoUsuario() == null || empleadoDto.getTipoUsuario().equals("Empleado")) ? true : false);

        return empleado;
    }
}
