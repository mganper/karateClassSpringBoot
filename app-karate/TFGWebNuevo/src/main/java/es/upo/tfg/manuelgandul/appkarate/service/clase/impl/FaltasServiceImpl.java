package es.upo.tfg.manuelgandul.appkarate.service.clase.impl;

import es.upo.tfg.manuelgandul.appkarate.converter.alumno.AlumnoConverter;
import es.upo.tfg.manuelgandul.appkarate.converter.clase.ClaseConverter;
import es.upo.tfg.manuelgandul.appkarate.converter.clase.FaltasConverter;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.FaltasDto;
import es.upo.tfg.manuelgandul.appkarate.repository.clase.FaltasJpaRepository;
import es.upo.tfg.manuelgandul.appkarate.service.clase.ClaseService;
import es.upo.tfg.manuelgandul.appkarate.service.clase.FaltasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("faltasService")
public class FaltasServiceImpl implements FaltasService {

    @Autowired
    @Qualifier("faltasJpaRepository")
    private FaltasJpaRepository faltasJpaRepository;

    @Autowired
    @Qualifier("faltasConverter")
    private FaltasConverter faltasConverter;

    @Autowired
    @Qualifier("alumnoConverter")
    private AlumnoConverter alumnoConverter;

    @Autowired
    @Qualifier("claseConverter")
    private ClaseConverter claseConverter;

    @Override
    public List<FaltasDto> listFaltas() {
        List<FaltasDto> faltasDtoList = new ArrayList<>();

        faltasJpaRepository.findAll().stream().forEach((falta) -> {
            faltasDtoList.add(faltasConverter.entity2model(falta));
        });

        return faltasDtoList;
    }

    @Override
    public List<FaltasDto> listFaltasByAlumno(AlumnoDto alumnoDto) {
        List<FaltasDto> faltasDtoList = new ArrayList<>();

        faltasJpaRepository.findAllByAlumnoClase_AlumnoOrderByFecha(alumnoConverter.model2Entity(alumnoDto)).stream().forEach((falta) -> {
            faltasDtoList.add(faltasConverter.entity2model(falta));
        });

        return faltasDtoList;
    }

    @Override
    public List<FaltasDto> listFaltasByClase(ClaseDto claseDto) {
        List<FaltasDto> faltasDtoList = new ArrayList<>();

        faltasJpaRepository.findAllByAlumnoClase_ClaseOrderByFecha(claseConverter.model2entity(claseDto)).stream().forEach((falta) -> {
            faltasDtoList.add(faltasConverter.entity2model(falta));
        });

        return faltasDtoList;
    }

    @Override
    public FaltasDto addFalta(FaltasDto faltasDto) {
        faltasDto = faltasConverter.entity2model(faltasJpaRepository.save(faltasConverter.model2entity(faltasDto)));

        return faltasDto;
    }

    @Override
    public void removeFalta(FaltasDto faltasDto) {
        faltasJpaRepository.delete(faltasConverter.model2entity(faltasDto));
    }

    @Override
    public FaltasDto updateFalta(FaltasDto faltasDto) {
        faltasDto = faltasConverter.entity2model(faltasJpaRepository.save(faltasConverter.model2entity(faltasDto)));

        return faltasDto;
    }

    @Override
    public FaltasDto getFaltaById(int id) {
        return faltasConverter.entity2model(faltasJpaRepository.findById(id));
    }
}
