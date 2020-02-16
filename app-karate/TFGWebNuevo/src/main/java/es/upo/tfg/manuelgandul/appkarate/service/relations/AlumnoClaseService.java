package es.upo.tfg.manuelgandul.appkarate.service.relations;

import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;
import es.upo.tfg.manuelgandul.appkarate.model.relations.AlumnoClaseDto;

import java.util.List;

public interface AlumnoClaseService {
    public List<AlumnoDto> listAlumnosByClase(ClaseDto claseDto);

    public AlumnoClaseDto addAlumnoLista(AlumnoDto alumnoDto, ClaseDto claseDto);

    public void removeAlumnoCentro(AlumnoDto alumnoDto);

    public AlumnoClaseDto getAlumnoClaseById(int id);
}
