package es.upo.tfg.manuelgandul.appkarate.converter.alumno;


import es.upo.tfg.manuelgandul.appkarate.converter.common.CinturonConverter;
import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Alumno;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component("alumnoConverter")
public class AlumnoConverter {

    @Autowired
    @Qualifier("cinturonConverter")
    private CinturonConverter cinturonConverter;

    public AlumnoDto entity2Model(Alumno alumno) {
        AlumnoDto alumnoDto = null;

        if(null != alumno) {
            alumnoDto = new AlumnoDto();

            alumnoDto.setId(alumno.getId());
            alumnoDto.setDni(alumno.getDni());
            alumnoDto.setNombre(alumno.getNombre());
            alumnoDto.setApellidos(alumno.getApellidos());
            alumnoDto.setTlf(alumno.getTelefono_contacto());
            alumnoDto.setSexo(alumno.getSexo());
            alumnoDto.setDir(alumno.getDireccion());
            alumnoDto.setFechaNac(alumno.getFecha_nacimiento().toLocalDate());
            alumnoDto.setCint(cinturonConverter.entity2model(alumno.getCinturon()));

            String activo = (alumno.isActivo()) ? "Activo" : "Inactivo";

            alumnoDto.setActivo(activo);
        }

        return alumnoDto;
    }

    public Alumno model2Entity(AlumnoDto alumnoDto) {
        Alumno alumno = null;

        if(null != alumnoDto) {
            alumno = new Alumno();
            LocalDate date = alumnoDto.getFechaNac().plusDays(1);

            alumno.setId(alumnoDto.getId());
            alumno.setDni(alumnoDto.getDni());
            alumno.setNombre(alumnoDto.getNombre());
            alumno.setApellidos(alumnoDto.getApellidos());
            alumno.setTelefono_contacto(alumnoDto.getTlf());
            alumno.setSexo(alumnoDto.getSexo());
            alumno.setDireccion(alumnoDto.getDir());
            alumno.setFecha_nacimiento(Date.valueOf(date));
            alumno.setCinturon(cinturonConverter.model2entity(alumnoDto.getCint()));

            Boolean activo = (alumnoDto.getActivo() == null || alumnoDto.getActivo().equals("Activo")) ? true : false;

            alumno.setActivo(activo);
        }

        return alumno;
    }
}
