package es.upo.tfg.manuelgandul.appkarate.service;

import es.upo.tfg.manuelgandul.appkarate.model.AlumnoModel;

import java.util.List;

public interface AlumnoService {
    public List<AlumnoModel> listAllAlumnos();

    public AlumnoModel addAlumno(AlumnoModel alumno);

    public void removeAlumno(AlumnoModel alumno);

    public AlumnoModel updateAlumno(AlumnoModel alumno);
}
