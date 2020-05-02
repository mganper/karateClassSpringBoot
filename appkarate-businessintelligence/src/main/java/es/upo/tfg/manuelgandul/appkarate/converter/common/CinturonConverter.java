package es.upo.tfg.manuelgandul.appkarate.converter.common;

import es.upo.tfg.manuelgandul.appkarate.entity.common.Cinturon;
import es.upo.tfg.manuelgandul.appkarate.model.common.CinturonDto;
import org.springframework.stereotype.Component;

@Component("cinturonConverter")
public class CinturonConverter {

    public CinturonDto entity2model(Cinturon cinturon){
        CinturonDto cinturonDto = null;

        if(null != cinturon) {
            cinturonDto = new CinturonDto();

            cinturonDto.setId(cinturon.getId());
            cinturonDto.setNomnbre(cinturon.getNomnbre());
        }

        return cinturonDto;
    }

    public Cinturon model2entity(CinturonDto cinturonDto){
        Cinturon cinturon = null;

        if (null != cinturonDto) {
            cinturon = new Cinturon();

            cinturon.setId(cinturonDto.getId());
            cinturon.setNomnbre(cinturonDto.getNomnbre());
        }

        return cinturon;
    }
}
