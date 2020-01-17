package es.upo.tfg.manuelgandul.appkarate.converter.alumno;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Observacion;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.ObservacionDto;
import org.springframework.stereotype.Component;

@Component("observacionConverter")
public class ObservacionConverter {

    public ObservacionDto entity2Model(Observacion observacion){
        ObservacionDto observacionDto = new ObservacionDto();

        observacionDto.setId(observacion.getId());
        observacionDto.setAlumno(observacion.getAlumno());
        observacionDto.setDato(observacion.getDato());
        observacionDto.setFecha(observacion.getFecha());

        return observacionDto;
    }

    public Observacion model2Entity(ObservacionDto observacionDto){
        Observacion observacion = new Observacion();

        observacion.setId(observacionDto.getId());
        observacion.setAlumno(observacionDto.getAlumno());
        observacion.setDato(observacionDto.getDato());
        observacion.setFecha(observacionDto.getFecha());

        return observacion;
    }
}
