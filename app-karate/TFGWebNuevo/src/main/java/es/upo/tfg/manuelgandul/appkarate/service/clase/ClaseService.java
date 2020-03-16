package es.upo.tfg.manuelgandul.appkarate.service.clase;

import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.centro.CentroDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;

import java.util.List;

public interface ClaseService {
    List<ClaseDto> listClases();

    ClaseDto addClase(ClaseDto claseDto);

    void removeClase(ClaseDto claseDto);

    ClaseDto updateClase(ClaseDto claseDto);

    ClaseDto getClaseById(int id);

    void setBajaAllClasesByCentro(CentroDto centroDto);

    int getNumeroClasesByCentro(CentroDto centroDto);
}
