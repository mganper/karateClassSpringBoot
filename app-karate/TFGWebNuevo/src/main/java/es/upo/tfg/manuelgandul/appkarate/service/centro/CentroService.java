package es.upo.tfg.manuelgandul.appkarate.service.centro;

import es.upo.tfg.manuelgandul.appkarate.model.centro.CentroDto;

import java.util.List;

public interface CentroService {

    List<CentroDto> listCentros();

    CentroDto addCentro(CentroDto centroDto);

    void removeCentro(CentroDto centroDto);

    CentroDto updateCentro(CentroDto centroDto);

    CentroDto getCentroById(int id);
}
