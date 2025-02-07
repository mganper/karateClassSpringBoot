package es.upo.tfg.manuelgandul.appkarate.service.alumno.impl;

import es.upo.tfg.manuelgandul.appkarate.converter.alumno.ObservacionConverter;
import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Observacion;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.ObservacionDto;
import es.upo.tfg.manuelgandul.appkarate.repository.alumno.ObservacionJpaRepository;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.ObservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("observacionService")
public class ObservacionServiceImpl implements ObservacionService {

    @Autowired
    @Qualifier("observacionJpaRepository")
    private ObservacionJpaRepository observacionJpaRepository;

    @Autowired
    @Qualifier("observacionConverter")
    private ObservacionConverter observacionConverter;

    /**
     * @param alumnoDto
     * @return
     */
    @Override
    public List<ObservacionDto> listObservacionAlumno(AlumnoDto alumnoDto) {
        List<ObservacionDto> listObservacionDto = new ArrayList<>();

        observacionJpaRepository.findAllByAlumno_IdOrderByFecha(alumnoDto.getId()).stream().forEach((obs) ->{
            listObservacionDto.add(observacionConverter.entity2Model(obs));
        });

        return listObservacionDto;
    }

    @Override
    public ObservacionDto addObservacion(ObservacionDto observacionDto) {
        observacionDto = observacionConverter.entity2Model(observacionJpaRepository.save((observacionConverter.model2Entity(observacionDto))));
        return observacionDto;
    }

    @Override
    public void removeObservacion(ObservacionDto observacionDto) {
        observacionJpaRepository.delete(observacionConverter.model2Entity(observacionDto));
    }

    @Override
    public ObservacionDto updateObservacion(ObservacionDto observacionDto) {
        observacionDto = observacionConverter.entity2Model(observacionJpaRepository.save((observacionConverter.model2Entity(observacionDto))));
        return observacionDto;
    }

    @Override
    public ObservacionDto getObservacionById(int id) {
        Observacion observacion = observacionJpaRepository.findById(id);

        return observacionConverter.entity2Model(observacion);
    }
}
