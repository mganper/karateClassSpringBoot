package es.upo.tfg.manuelgandul.appkarate.service.clase;


import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.FaltasDto;

import java.util.List;

public interface FaltasService {
    public List<FaltasDto> listFaltas();

    public List<FaltasDto> listFaltasByAlumno(AlumnoDto alumnoDto);

    public FaltasDto addFalta(FaltasDto faltasDto);

    public void removeFalta(FaltasDto faltasDto);

    public FaltasDto updateFalta(FaltasDto faltasDto);

    public FaltasDto getFaltaById(int id);
}
