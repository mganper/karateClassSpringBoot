package es.upo.tfg.manuelgandul.appkarate.service.alumno.impl;

import es.upo.tfg.manuelgandul.appkarate.converter.alumno.AlumnoConverter;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.repository.alumno.AlumnoJpaRepository;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * En esta clase va la lógica de alumno, y se llama desde el controller.
 */
@Service("alumnoService")
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    @Qualifier("alumnoJpaRepository")
    private AlumnoJpaRepository alumnoJpaRepository;

    @Autowired
    @Qualifier("alumnoConverter")
    private AlumnoConverter alumnoConverter;

    @Override
    public List<AlumnoDto> listAllAlumnos() {
        List<AlumnoDto> listAlumnoDto = new ArrayList<>();

        alumnoJpaRepository.findAll().stream().forEach((al) ->{
            listAlumnoDto.add(alumnoConverter.entity2Model(al));
        });

        return listAlumnoDto;
    }

    /**
     *
     * @param alumno
     * @return
     */
    @Override
    public AlumnoDto addAlumno(AlumnoDto alumno) {
        alumnoJpaRepository.save(alumnoConverter.model2Entity(alumno));
        return alumno;
    }

    @Override
    public void removeAlumno(AlumnoDto alumno) {
        alumnoJpaRepository.delete(alumnoConverter.model2Entity(alumno));
    }

    @Override
    public AlumnoDto updateAlumno(AlumnoDto alumno) {
        alumnoJpaRepository.save(alumnoConverter.model2Entity(alumno));
        return alumno;
    }
}
