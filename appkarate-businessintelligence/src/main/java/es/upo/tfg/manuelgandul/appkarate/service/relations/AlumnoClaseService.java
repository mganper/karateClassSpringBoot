package es.upo.tfg.manuelgandul.appkarate.service.relations;

import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.centro.CentroDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ListaClaseDto;
import es.upo.tfg.manuelgandul.appkarate.model.relations.AlumnoClaseDto;

import java.util.List;

public interface AlumnoClaseService {
    List<AlumnoClaseDto> listAllAlumnoClase();

    List<AlumnoDto> listAlumnosByClase(ClaseDto claseDto);

    ClaseDto getClaseByAlumno(AlumnoDto alumnoDto);

    AlumnoClaseDto getAlumnoClaseByAlumno(AlumnoDto alumnoDto);

    AlumnoClaseDto getAlumnoClaseByAlumnoId(int idAlumnoDto);

    AlumnoClaseDto addAlumnoLista(AlumnoDto alumnoDto, ClaseDto claseDto);

    void removeAlumnoCentro(AlumnoDto alumnoDto);

    List<AlumnoClaseDto> listAllAlumnoClaseByClase(ClaseDto claseDto);

    int getNumeroAlumnosByClase(ClaseDto claseDto);

    void removeAllAlumnosByClase(ClaseDto claseDto);

    void removeAllAlumnosByCentro(CentroDto centroDto);

    void updateListaAlumnos(ListaClaseDto listaClaseDto);
}
