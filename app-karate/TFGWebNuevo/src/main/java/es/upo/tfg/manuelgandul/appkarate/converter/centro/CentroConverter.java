package es.upo.tfg.manuelgandul.appkarate.converter.centro;

import es.upo.tfg.manuelgandul.appkarate.entity.centro.Centro;
import es.upo.tfg.manuelgandul.appkarate.model.centro.CentroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("centroConverter")
public class CentroConverter {

    @Autowired
    @Qualifier("responsableConverter")
    private ResponsableConverter responsableConverter;

    public CentroDto entity2model(Centro centro){
        CentroDto centroDto = new CentroDto();

        centroDto.setId(centro.getId());
        centroDto.setNombre(centro.getNombre());
        centroDto.setDireccion(centro.getDireccion());
        centroDto.setHoraMaximaInicio(centro.getHora_maxima_inicio());
        centroDto.setHoraMaximaFin(centro.getHora_maxima_fin());
        centroDto.setMaxClases(centro.getMax_clases());
        centroDto.setPrecioMes(centro.getPrecio_mes());
        centroDto.setResponsable(responsableConverter.entity2model(centro.getResponsable()));

        String activo = (centro.isActivo()) ? "Activo" : "Inactivo";

        centroDto.setActivo(activo);

        return  centroDto;
    }

    public Centro model2entity(CentroDto centroDto){
        Centro centro = new Centro();

        centro.setId(centroDto.getId());
        centro.setNombre(centroDto.getNombre());
        centro.setDireccion(centroDto.getDireccion());
        centro.setHora_maxima_inicio(centroDto.getHoraMaximaInicio());
        centro.setHora_maxima_inicio(centroDto.getHoraMaximaFin());
        centro.setMax_clases(centroDto.getMaxClases());
        centro.setPrecio_mes(centroDto.getPrecioMes());
        centro.setResponsable(responsableConverter.model2entity(centroDto.getResponsable()));

        Boolean activo = (centroDto.getActivo() == null || centroDto.getActivo().equals("Activo")) ? true : false;

        centro.setActivo(activo);

        return centro;
    }
}
