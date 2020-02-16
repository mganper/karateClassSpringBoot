package es.upo.tfg.manuelgandul.appkarate.service.centro;

import es.upo.tfg.manuelgandul.appkarate.model.centro.CentroDto;

import java.util.List;

public interface CentroService {

    public List<CentroDto> listCentros();

    public CentroDto addCentro(CentroDto centroDto);

    public void removeCentro(CentroDto centroDto);

    public CentroDto updateCentro(CentroDto centroDto);

    public CentroDto getCentroById(int id);

}
