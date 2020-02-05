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

    @Autowired
    @Qualifier("alumnoConverter")
    private AlumnoConverter alumnoConverter;

    @Autowired
    @Qualifier("claseConverter")
    private ClaseConverter claseConverter;

    public AlumnoClaseDto entity2model(AlumnoClase alumnoClase){
        AlumnoClaseDto alumnoClaseDto = new AlumnoClaseDto();

        alumnoClaseDto.setId(alumnoClase.getId());
        alumnoClaseDto.setAlumno(alumnoConverter.entity2Model(alumnoClase.getAlumno()));
        alumnoClaseDto.setClase(claseConverter.entity2model(alumnoClase.getClase()));

        return alumnoClaseDto;
    }

    public AlumnoClase model2entity(AlumnoClaseDto alumnoClaseDto){
        AlumnoClase alumnoClase = new AlumnoClase();

        alumnoClase.setId(alumnoClaseDto.getId());
        alumnoClase.setAlumno(alumnoConverter.model2Entity(alumnoClaseDto.getAlumno()));
        alumnoClase.setClase(claseConverter.model2entity(alumnoClaseDto.getClase()));

        return alumnoClase;
    }

}
