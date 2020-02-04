package es.upo.tfg.manuelgandul.appkarate.converter.clase;

import es.upo.tfg.manuelgandul.appkarate.entity.clase.Faltas;
import es.upo.tfg.manuelgandul.appkarate.model.clase.FaltasDto;
import org.springframework.stereotype.Component;

@Component("faltasConverter")
public class FaltasConverter {

    public FaltasDto entity2model(Faltas faltas){
        FaltasDto faltasDto = new FaltasDto();

        faltasDto.setId(faltas.getId());
        faltasDto.setFecha(faltas.getFecha());
        faltasDto.setAlumnoClase(faltas.getAlumnoClase());

        return faltasDto;
    }

    public Faltas model2entity(FaltasDto faltasDto){
        Faltas faltas = new Faltas();

        faltas.setId(faltasDto.getId());
        faltas.setFecha(faltasDto.getFecha());
        faltas.setAlumnoClase(faltasDto.getAlumnoClase());

        return faltas;
    }


}
