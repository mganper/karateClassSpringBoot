package es.upo.tfg.manuelgandul.appkarate.service.centro.impl;

import es.upo.tfg.manuelgandul.appkarate.converter.centro.CentroConverter;
import es.upo.tfg.manuelgandul.appkarate.entity.centro.Centro;
import es.upo.tfg.manuelgandul.appkarate.model.centro.CentroDto;
import es.upo.tfg.manuelgandul.appkarate.repository.centro.CentroJpaRepository;
import es.upo.tfg.manuelgandul.appkarate.service.centro.CentroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("centroService")
public class CentroServiceImpl implements CentroService {

    @Autowired
    @Qualifier("centroJpaRepository")
    private CentroJpaRepository centroJpaRepository;

    @Autowired
    @Qualifier("centroConverter")
    private CentroConverter centroConverter;

    @Override
    public List<CentroDto> listCentros() {
        List<CentroDto> centroDtoList = new ArrayList<>();

        centroJpaRepository.findAll().stream().forEach((centro) -> {
            centroDtoList.add(centroConverter.entity2model(centro));
        });

        return  centroDtoList;
    }

    @Override
    public CentroDto addCentro(CentroDto centroDto) {
        Centro centro = centroConverter.model2entity(centroDto);
        centro = centroJpaRepository.save(centro);
        centroDto = centroConverter.entity2model(centro);

        return centroDto;
    }

    @Override
    public void removeCentro(CentroDto centroDto) {
        centroJpaRepository.delete(centroConverter.model2entity(centroDto));
    }

    @Override
    public CentroDto updateCentro(CentroDto centroDto) {
        centroDto = centroConverter.entity2model(centroJpaRepository.save(centroConverter.model2entity(centroDto)));

        return centroDto;
    }

    @Override
    public CentroDto getCentroById(int id) {
        return centroConverter.entity2model(centroJpaRepository.findById(id));
    }
}
