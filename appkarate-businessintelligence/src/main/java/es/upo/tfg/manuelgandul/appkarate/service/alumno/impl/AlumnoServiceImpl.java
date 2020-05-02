package es.upo.tfg.manuelgandul.appkarate.service.alumno.impl;

import es.upo.tfg.manuelgandul.appkarate.converter.alumno.AlumnoConverter;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.repository.alumno.AlumnoJpaRepository;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * En esta clase va la lógica de alumno, y se llama desde el controller.
 */
@Transactional
@Service("alumnoService")
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoJpaRepository alumnoJpaRepository;

    private final AlumnoConverter alumnoConverter;

    public AlumnoServiceImpl(@Qualifier("alumnoJpaRepository") AlumnoJpaRepository alumnoJpaRepository, @Qualifier("alumnoConverter") AlumnoConverter alumnoConverter) {
        this.alumnoJpaRepository = alumnoJpaRepository;
        this.alumnoConverter = alumnoConverter;
    }

    @Override
    public List<AlumnoDto> listAllAlumnos() {
        List<AlumnoDto> listAlumnoDto = new ArrayList<>();

        alumnoJpaRepository.findAll().stream().forEach((al) -> {
            listAlumnoDto.add(alumnoConverter.entity2Model(al));
        });

        return listAlumnoDto;
    }

    @Override
    public List<AlumnoDto> listAllAlumnosActivos() {
        List<AlumnoDto> listAlumnoDto = new ArrayList<>();

        alumnoJpaRepository.findAllByActivo(true).stream().forEach((al) -> {
            listAlumnoDto.add(alumnoConverter.entity2Model(al));
        });

        return listAlumnoDto;
    }

    @Override
    public AlumnoDto addAlumno(AlumnoDto alumno) {
        return alumnoConverter.entity2Model(alumnoJpaRepository.save(alumnoConverter.model2Entity(alumno)));
    }

    @Override
    public void removeAlumno(AlumnoDto alumno) {
        alumnoJpaRepository.delete(alumnoConverter.model2Entity(alumno));
    }

    @Override
    public AlumnoDto updateAlumno(AlumnoDto alumno) {
        alumno = alumnoConverter.entity2Model(alumnoJpaRepository.save(alumnoConverter.model2Entity(alumno)));
        return alumno;
    }

    @Override
    public AlumnoDto getAlumnoById(int id) {
        return alumnoConverter.entity2Model(alumnoJpaRepository.findById(id));
    }
}
