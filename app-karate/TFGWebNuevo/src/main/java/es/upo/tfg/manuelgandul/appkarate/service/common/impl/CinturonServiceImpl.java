package es.upo.tfg.manuelgandul.appkarate.service.common.impl;

import es.upo.tfg.manuelgandul.appkarate.converter.common.CinturonConverter;
import es.upo.tfg.manuelgandul.appkarate.model.common.CinturonDto;
import es.upo.tfg.manuelgandul.appkarate.repository.common.CinturonJpaRepository;
import es.upo.tfg.manuelgandul.appkarate.service.common.CinturonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("cinturonService")
public class CinturonServiceImpl implements CinturonService {

    @Autowired
    @Qualifier("cinturonJpaRepository")
    private CinturonJpaRepository cinturonJpaRepository;

    @Autowired
    @Qualifier("cinturonConverter")
    private CinturonConverter cinturonConverter;


    @Override
    public List<CinturonDto> listCinturon() {
        List<CinturonDto> cinturonDtoList = new ArrayList<>();

        cinturonJpaRepository.findAll().stream().forEach((cinturon) -> {
            cinturonDtoList.add(cinturonConverter.entity2model(cinturon));
        });

        return cinturonDtoList;
    }

    @Override
    public CinturonDto addCinturon(CinturonDto cinturonDto) {
        cinturonJpaRepository.save(cinturonConverter.model2entity(cinturonDto));

        return cinturonDto;
    }

    @Override
    public void removeCinturon(CinturonDto cinturonDto) {
        cinturonJpaRepository.delete(cinturonConverter.model2entity(cinturonDto));
    }

    @Override
    public CinturonDto updateCinturon(CinturonDto cinturonDto) {
        cinturonJpaRepository.save(cinturonConverter.model2entity(cinturonDto));

        return cinturonDto;
    }

    @Override
    public CinturonDto getCinturonById(int id) {
        return cinturonConverter.entity2model(cinturonJpaRepository.findById(id));
    }

    @Override
    public void comprobarCinturones(){
        if(cinturonJpaRepository.findAll().size() != 12){
            setCinturones();
        }
    }

    private void setCinturones(){
        String[] cinturones = new String[] {"Blanco", "Blanco-Amarillo", "Amarillo", "Amarillo-Naranja", "Naranja", "Naranja-Verde",
        "Verde", "Verde-Azul", "Azul", "Azul-Marron", "Marron", "Negro"};

        for(int i = 0; i < cinturones.length; i++){
            CinturonDto cinturon = new CinturonDto();

            cinturon.setNomnbre(cinturones[i]);

            cinturonJpaRepository.save(cinturonConverter.model2entity(cinturon));
        }
    }
}
