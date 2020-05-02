package es.upo.tfg.manuelgandul.appkarate.service.alumno;

import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;

import java.util.List;

public interface AlumnoService {
    List<AlumnoDto> listAllAlumnos();

    List<AlumnoDto> listAllAlumnosActivos();

    AlumnoDto addAlumno(AlumnoDto alumno);

    void removeAlumno(AlumnoDto alumno);

    AlumnoDto updateAlumno(AlumnoDto alumno);

    AlumnoDto getAlumnoById(int id);

}
