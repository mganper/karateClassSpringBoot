package es.upo.tfg.manuelgandul.appkarate.service.alumno;

import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.ObservacionDto;

import java.util.List;

public interface ObservacionService {
    public List<ObservacionDto> listObservacionAlumno(AlumnoDto alumnoDto);

    public ObservacionDto addObservacion(ObservacionDto observacionDto);

    public void removeObservacion(ObservacionDto observacionDto);

    public ObservacionDto updateObservacion(ObservacionDto observacionDto);
}
