package es.upo.tfg.manuelgandul.appkarate.converter.centro;

import es.upo.tfg.manuelgandul.appkarate.entity.centro.Responsable;
import es.upo.tfg.manuelgandul.appkarate.model.centro.ResponsableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("responsableConverter")
public class ResponsableConverter {

    @Autowired
    @Qualifier("centroConverter")
    private CentroConverter centroConverter;

    public ResponsableDto entity2model(Responsable responsable){
        ResponsableDto responsableDto = new ResponsableDto();

        responsableDto.setId(responsable.getId());
        responsableDto.setNombre(responsable.getNombre());
        responsableDto.setApellidos(responsable.getApellidos());
        responsableDto.setCargo(responsable.getCargo());
        responsableDto.setCorreo(responsable.getCorreo());
        responsableDto.setTelefono(responsable.getTelefono());
        responsableDto.setCentro(centroConverter.entity2model(responsable.getCentro()));

        return responsableDto;
    }

    public Responsable model2entity(ResponsableDto responsableDto){
        Responsable responsable = new Responsable();

        responsable.setId(responsableDto.getId());
        responsable.setNombre(responsableDto.getNombre());
        responsable.setApellidos(responsableDto.getApellidos());
        responsable.setCargo(responsableDto.getCargo());
        responsable.setCorreo(responsableDto.getCorreo());
        responsable.setTelefono(responsableDto.getTelefono());
        responsable.setCentro(centroConverter.model2entity(responsableDto.getCentro()));

        return responsable;
    }
}
