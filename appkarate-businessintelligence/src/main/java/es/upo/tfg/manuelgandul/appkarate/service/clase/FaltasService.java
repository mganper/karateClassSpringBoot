package es.upo.tfg.manuelgandul.appkarate.service.clase;


import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.FaltasDto;

import java.util.List;

public interface FaltasService {
    List<FaltasDto> listFaltas();

    List<FaltasDto> listFaltasByAlumno(AlumnoDto alumnoDto);

    List<FaltasDto> listFaltasByClase(ClaseDto claseDto);

    FaltasDto addFalta(FaltasDto faltasDto);

    void removeFalta(FaltasDto faltasDto);

    FaltasDto updateFalta(FaltasDto faltasDto);

    FaltasDto getFaltaById(int id);
}
