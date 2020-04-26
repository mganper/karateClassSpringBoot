package es.upo.tfg.manuelgandul.appkarate.converter.alumno;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Observacion;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.ObservacionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("observacionConverter")
public class ObservacionConverter {

    @Autowired
    @Qualifier("alumnoConverter")
    private AlumnoConverter alumnoConverter;

    public ObservacionDto entity2Model(Observacion observacion){
        ObservacionDto observacionDto = new ObservacionDto();

        observacionDto.setId(observacion.getId());
        observacionDto.setAlumno(alumnoConverter.entity2Model(observacion.getAlumno()));
        observacionDto.setDato(observacion.getDato());
        observacionDto.setFecha(observacion.getFecha());

        return observacionDto;
    }

    public Observacion model2Entity(ObservacionDto observacionDto){
        Observacion observacion = new Observacion();
        LocalDate date = observacionDto.getFecha().plusDays(1);

        observacion.setId(observacionDto.getId());
        observacion.setAlumno(alumnoConverter.model2Entity(observacionDto.getAlumno()));
        observacion.setDato(observacionDto.getDato());
        observacion.setFecha(date);

        return observacion;
    }
}
