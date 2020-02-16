package es.upo.tfg.manuelgandul.appkarate.service.centro;

import es.upo.tfg.manuelgandul.appkarate.model.centro.ResponsableDto;

import java.util.List;

public interface ResponsableService {

    public List<ResponsableDto> listResponsable();

    public ResponsableDto addResponsable(ResponsableDto responsableDto);

    public void removeResponsable(ResponsableDto responsableDto);

    public ResponsableDto updateResponsable(ResponsableDto responsableDto);

    public ResponsableDto getResponsableById(int id);
}
