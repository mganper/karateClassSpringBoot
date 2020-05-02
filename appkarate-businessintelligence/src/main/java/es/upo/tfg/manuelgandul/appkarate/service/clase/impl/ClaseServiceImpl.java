package es.upo.tfg.manuelgandul.appkarate.service.clase.impl;

import es.upo.tfg.manuelgandul.appkarate.converter.centro.CentroConverter;
import es.upo.tfg.manuelgandul.appkarate.converter.clase.ClaseConverter;
import es.upo.tfg.manuelgandul.appkarate.model.centro.CentroDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;
import es.upo.tfg.manuelgandul.appkarate.repository.clase.ClaseJpaRepository;
import es.upo.tfg.manuelgandul.appkarate.service.clase.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("claseService")
public class ClaseServiceImpl implements ClaseService {

    @Autowired
    @Qualifier("claseJpaRepository")
    private ClaseJpaRepository claseJpaRepository;

    @Autowired
    @Qualifier("claseConverter")
    private ClaseConverter claseConverter;

    @Autowired
    @Qualifier("centroConverter")
    private CentroConverter centroConverter;

    @Override
    public List<ClaseDto> listClases() {
        List<ClaseDto> claseDtoList = new ArrayList<>();

        claseJpaRepository.findAll().stream().forEach((clase) -> {
            claseDtoList.add(claseConverter.entity2model(clase));
        });

        return claseDtoList;
    }

    @Override
    public ClaseDto addClase(ClaseDto claseDto) {
        claseDto = claseConverter.entity2model(claseJpaRepository.save(claseConverter.model2entity(claseDto)));

        return claseDto;
    }

    @Override
    public void removeClase(ClaseDto claseDto) {
        claseJpaRepository.delete(claseConverter.model2entity(claseDto));
    }

    @Override
    public ClaseDto updateClase(ClaseDto claseDto) {
        claseDto = claseConverter.entity2model(claseJpaRepository.save(claseConverter.model2entity(claseDto)));

        return claseDto;
    }

    @Override
    public ClaseDto getClaseById(int id) {
        return claseConverter.entity2model(claseJpaRepository.findById(id));
    }

    @Override
    public void setBajaAllClasesByCentro(CentroDto centroDto) {
        claseJpaRepository.findAllByCentro(centroConverter.model2entity(centroDto)).stream().forEach(clase -> {
            clase.setActivo(false);
            claseJpaRepository.save(clase);
        });
    }

    @Override
    public int getNumeroClasesByCentro(CentroDto centroDto) {
        return claseJpaRepository.findAllByCentro(centroConverter.model2entity(centroDto)).size();
    }
}
