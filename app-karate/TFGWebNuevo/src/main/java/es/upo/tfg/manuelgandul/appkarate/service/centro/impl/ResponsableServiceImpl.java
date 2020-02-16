package es.upo.tfg.manuelgandul.appkarate.service.centro.impl;

import es.upo.tfg.manuelgandul.appkarate.converter.centro.ResponsableConverter;
import es.upo.tfg.manuelgandul.appkarate.model.centro.ResponsableDto;
import es.upo.tfg.manuelgandul.appkarate.repository.centro.ResponsableJpaRepository;
import es.upo.tfg.manuelgandul.appkarate.service.centro.ResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("responsableService")
public class ResponsableServiceImpl implements ResponsableService {

    @Autowired
    @Qualifier("responsableJpaRepository")
    private ResponsableJpaRepository responsableJpaRepository;

    @Autowired
    @Qualifier("responsableConverter")
    private ResponsableConverter responsableConverter;

    @Override
    public List<ResponsableDto> listResponsable() {
        List<ResponsableDto> responsableDtoList = new ArrayList<>();

        responsableJpaRepository.findAll().stream().forEach((res) ->{
            responsableDtoList.add(responsableConverter.entity2model(res));
        });

        return responsableDtoList;
    }

    @Override
    public ResponsableDto addResponsable(ResponsableDto responsableDto) {
        responsableJpaRepository.save(responsableConverter.model2entity(responsableDto));

        return  responsableDto;
    }

    @Override
    public void removeResponsable(ResponsableDto responsableDto) {
        responsableJpaRepository.delete(responsableConverter.model2entity(responsableDto));
    }

    @Override
    public ResponsableDto updateResponsable(ResponsableDto responsableDto) {
        responsableJpaRepository.save(responsableConverter.model2entity(responsableDto));

        return responsableDto;
    }

    @Override
    public ResponsableDto getResponsableById(int id) {
        return responsableConverter.entity2model(responsableJpaRepository.findById(id));
    }
}
