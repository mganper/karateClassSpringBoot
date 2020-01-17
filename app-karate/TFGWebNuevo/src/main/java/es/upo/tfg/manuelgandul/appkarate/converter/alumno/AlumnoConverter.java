package es.upo.tfg.manuelgandul.appkarate.converter.alumno;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Alumno;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import org.springframework.stereotype.Component;

@Component("alumnoConverter")
public class AlumnoConverter {

    public AlumnoDto entity2Model(Alumno alumno) {
        AlumnoDto alumnoDto = new AlumnoDto();

        alumnoDto.setId(alumno.getId());
        alumnoDto.setDni(alumno.getDni());
        alumnoDto.setNombre(alumno.getNombre());
        alumnoDto.setApellidos(alumno.getApellidos());
        alumnoDto.setTlf(alumno.getTelefono_contacto());
        alumnoDto.setSexo(alumno.getSexo());
        alumnoDto.setDir(alumno.getDireccion());
        alumnoDto.setFechaNac(alumno.getFecha_nacimiento());
        alumnoDto.setCint(alumno.getCinturon());

        String activo = (alumno.isActivo()) ? "Activo" : "Inactivo";

        alumnoDto.setActivo(activo);

        return alumnoDto;
    }

    public Alumno model2Entity(AlumnoDto alumnoDto) {
        Alumno alumno = new Alumno();

        alumno.setId(alumnoDto.getId());
        alumno.setDni(alumnoDto.getDni());
        alumno.setNombre(alumnoDto.getNombre());
        alumno.setApellidos(alumnoDto.getApellidos());
        alumno.setTelefono_contacto(alumnoDto.getTlf());
        alumno.setSexo(alumnoDto.getSexo());
        alumno.setDireccion(alumnoDto.getDir());
        alumno.setCinturon(alumnoDto.getCint());

        Boolean activo = (alumnoDto.getActivo() == null || alumnoDto.getActivo().equals("Activo")) ? true : false;

        alumno.setActivo(activo);

        return alumno;
    }
}
