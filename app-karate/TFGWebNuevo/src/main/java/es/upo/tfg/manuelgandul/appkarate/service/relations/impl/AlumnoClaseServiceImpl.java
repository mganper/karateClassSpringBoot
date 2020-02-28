package es.upo.tfg.manuelgandul.appkarate.service.relations.impl;

import es.upo.tfg.manuelgandul.appkarate.converter.alumno.AlumnoConverter;
import es.upo.tfg.manuelgandul.appkarate.converter.relations.AlumnoClaseConverter;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;
import es.upo.tfg.manuelgandul.appkarate.model.relations.AlumnoClaseDto;
import es.upo.tfg.manuelgandul.appkarate.repository.relations.AlumnoClaseJpaRepository;
import es.upo.tfg.manuelgandul.appkarate.service.relations.AlumnoClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("alumnoClaseService")
public class AlumnoClaseServiceImpl implements AlumnoClaseService {

    @Autowired
    @Qualifier("alumnoClaseJpaRepository")
    private AlumnoClaseJpaRepository alumnoClaseJpaRepository;

    @Autowired
    @Qualifier("alumnoClaseConverter")
    private AlumnoClaseConverter alumnoClaseConverter;

    @Autowired
    @Qualifier("alumnoConverter")
    private AlumnoConverter alumnoConverter;

    @Override
    public List<AlumnoDto> listAlumnosByClase(ClaseDto claseDto) {
        List<AlumnoDto> alumnoDtoList = new ArrayList<>();

        alumnoClaseJpaRepository.findAll().stream().forEach((alClas) -> {
            alumnoDtoList.add(alumnoConverter.entity2Model(alClas.getAlumno()));
        });

        return alumnoDtoList;
    }

    @Override
    public AlumnoClaseDto addAlumnoLista(AlumnoDto alumnoDto, ClaseDto claseDto) {
        AlumnoClaseDto alumnoClaseDto = new AlumnoClaseDto();

        alumnoClaseDto.setAlumno(alumnoDto);
        alumnoClaseDto.setClase(claseDto);

        alumnoClaseDto = alumnoClaseConverter.entity2model(alumnoClaseJpaRepository.save(alumnoClaseConverter.model2entity(alumnoClaseDto)));

        return alumnoClaseDto;
    }

    @Override
    public void removeAlumnoCentro(AlumnoDto alumnoDto) {
        alumnoClaseJpaRepository.removeByAlumno(alumnoConverter.model2Entity(alumnoDto));
    }

    @Override
    public AlumnoClaseDto getAlumnoClaseById(int id) {
        return alumnoClaseConverter.entity2model(alumnoClaseJpaRepository.findById(id));
    }
}