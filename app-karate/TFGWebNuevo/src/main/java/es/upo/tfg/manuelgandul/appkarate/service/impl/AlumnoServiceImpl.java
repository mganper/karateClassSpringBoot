package es.upo.tfg.manuelgandul.appkarate.service.impl;

import es.upo.tfg.manuelgandul.appkarate.converter.AlumnoConverter;
import es.upo.tfg.manuelgandul.appkarate.model.AlumnoModel;
import es.upo.tfg.manuelgandul.appkarate.repository.AlumnoJpaRepository;
import es.upo.tfg.manuelgandul.appkarate.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * En esta clase va la lógica de alumno, y se llama desde el controller.
 */
@Service("AlumnoService")
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    @Qualifier("alumnoJpaRepository")
    private AlumnoJpaRepository alumnoJpaRepository;

    @Autowired
    @Qualifier("alumnoConverter")
    private AlumnoConverter alumnoConverter;

    @Override
    public List<AlumnoModel> listAllAlumnos() {
        List<AlumnoModel> listAlumnoModel = new ArrayList<>();

        alumnoJpaRepository.findAll().stream().forEach((al) ->{
            listAlumnoModel.add(alumnoConverter.entity2Model(al));
        });

        return listAlumnoModel;
    }

    @Override
    public AlumnoModel addAlumno(AlumnoModel alumno) {
        alumnoJpaRepository.save(alumnoConverter.model2Entity(alumno));
        return alumno;
    }

    @Override
    public void removeAlumno(AlumnoModel alumno) {
        alumnoJpaRepository.delete(alumnoConverter.model2Entity(alumno));
    }

    @Override
    public AlumnoModel updateAlumno(AlumnoModel alumno) {
        alumnoJpaRepository.save(alumnoConverter.model2Entity(alumno));
        return alumno;
    }
}
