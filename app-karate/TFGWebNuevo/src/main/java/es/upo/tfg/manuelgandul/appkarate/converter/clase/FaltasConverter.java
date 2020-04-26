package es.upo.tfg.manuelgandul.appkarate.converter.clase;

import es.upo.tfg.manuelgandul.appkarate.converter.relations.AlumnoClaseConverter;
import es.upo.tfg.manuelgandul.appkarate.entity.clase.Faltas;
import es.upo.tfg.manuelgandul.appkarate.model.clase.FaltasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("faltasConverter")
public class FaltasConverter {

    @Autowired
    @Qualifier("alumnoClaseConverter")
    private AlumnoClaseConverter alumnoClaseConverter;

    public FaltasDto entity2model(Faltas faltas){
        FaltasDto faltasDto = new FaltasDto();

        faltasDto.setId(faltas.getId());
        faltasDto.setFecha(faltas.getFecha());
        faltasDto.setAlumnoClase(alumnoClaseConverter.entity2model(faltas.getAlumnoClase()));

        return faltasDto;
    }

    public Faltas model2entity(FaltasDto faltasDto){
        Faltas faltas = new Faltas();
        LocalDate date = faltasDto.getFecha().plusDays(1);

        faltas.setId(faltasDto.getId());
        faltas.setFecha(date);
        faltas.setAlumnoClase(alumnoClaseConverter.model2entity(faltasDto.getAlumnoClase()));

        return faltas;
    }


}
