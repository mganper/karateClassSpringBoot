package es.upo.tfg.manuelgandul.appkarate.service.clase;

import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;

import java.util.List;

public interface ClaseService {
    public List<ClaseDto> listClases();

    public ClaseDto addClase(ClaseDto claseDto);

    public void removeClase(ClaseDto claseDto);

    public ClaseDto updateClase(ClaseDto claseDto);

    public ClaseDto getClaseById(int id);
}
