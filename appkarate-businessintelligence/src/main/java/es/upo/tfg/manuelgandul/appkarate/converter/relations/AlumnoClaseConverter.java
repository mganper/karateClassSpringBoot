package es.upo.tfg.manuelgandul.appkarate.converter.relations;

import es.upo.tfg.manuelgandul.appkarate.converter.alumno.AlumnoConverter;
import es.upo.tfg.manuelgandul.appkarate.converter.clase.ClaseConverter;
import es.upo.tfg.manuelgandul.appkarate.entity.relations.AlumnoClase;
import es.upo.tfg.manuelgandul.appkarate.model.relations.AlumnoClaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("alumnoClaseConverter")
public class AlumnoClaseConverter {

    private final AlumnoConverter alumnoConverter;

    private final ClaseConverter claseConverter;

    public AlumnoClaseConverter(@Qualifier("alumnoConverter") AlumnoConverter alumnoConverter, @Qualifier("claseConverter") ClaseConverter claseConverter) {
        this.alumnoConverter = alumnoConverter;
        this.claseConverter = claseConverter;
    }

    public AlumnoClaseDto entity2model(AlumnoClase alumnoClase){
        AlumnoClaseDto alumnoClaseDto = null;

        if (null != alumnoClase) {
            alumnoClaseDto = new AlumnoClaseDto();

            alumnoClaseDto.setId(alumnoClase.getId());
            alumnoClaseDto.setAlumno(alumnoConverter.entity2Model(alumnoClase.getAlumno()));
            alumnoClaseDto.setClase(claseConverter.entity2model(alumnoClase.getClase()));
        }

        return alumnoClaseDto;
    }

    public AlumnoClase model2entity(AlumnoClaseDto alumnoClaseDto){
        AlumnoClase alumnoClase = null;

        if (null != alumnoClaseDto) {
            alumnoClase = new AlumnoClase();

            alumnoClase.setId(alumnoClaseDto.getId());
            alumnoClase.setAlumno(alumnoConverter.model2Entity(alumnoClaseDto.getAlumno()));
            alumnoClase.setClase(claseConverter.model2entity(alumnoClaseDto.getClase()));
        }

        return alumnoClase;
    }

}
