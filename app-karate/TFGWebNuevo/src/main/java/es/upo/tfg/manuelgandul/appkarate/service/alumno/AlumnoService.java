package es.upo.tfg.manuelgandul.appkarate.service.alumno;

import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;

import java.util.List;

public interface AlumnoService {
    public List<AlumnoDto> listAllAlumnos();

    public AlumnoDto addAlumno(AlumnoDto alumno);

    public void removeAlumno(AlumnoDto alumno);

    public AlumnoDto updateAlumno(AlumnoDto alumno);
}
