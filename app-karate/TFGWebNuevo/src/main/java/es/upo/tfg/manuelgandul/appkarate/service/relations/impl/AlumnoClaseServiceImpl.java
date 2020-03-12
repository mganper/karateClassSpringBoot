package es.upo.tfg.manuelgandul.appkarate.service.relations.impl;

import es.upo.tfg.manuelgandul.appkarate.converter.alumno.AlumnoConverter;
import es.upo.tfg.manuelgandul.appkarate.converter.clase.ClaseConverter;
import es.upo.tfg.manuelgandul.appkarate.converter.relations.AlumnoClaseConverter;
import es.upo.tfg.manuelgandul.appkarate.entity.clase.Clase;
import es.upo.tfg.manuelgandul.appkarate.entity.relations.AlumnoClase;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.centro.CentroDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ListaClaseDto;
import es.upo.tfg.manuelgandul.appkarate.model.relations.AlumnoClaseDto;
import es.upo.tfg.manuelgandul.appkarate.repository.relations.AlumnoClaseJpaRepository;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.AlumnoService;
import es.upo.tfg.manuelgandul.appkarate.service.clase.ClaseService;
import es.upo.tfg.manuelgandul.appkarate.service.relations.AlumnoClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
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

    @Autowired
    @Qualifier("claseConverter")
    private ClaseConverter claseConverter;

    @Autowired
    @Qualifier("claseService")
    private ClaseService claseService;

    @Autowired
    @Qualifier("alumnoService")
    private AlumnoService alumnoService;

    @Override
    public List<AlumnoClaseDto> listAllAlumnoClase() {
        List<AlumnoClaseDto> alumnoClaseDtoList = new ArrayList<>();

        alumnoClaseJpaRepository.findAll().stream().forEach((alClas) -> {
            alumnoClaseDtoList.add(alumnoClaseConverter.entity2model(alClas));
        });

        return alumnoClaseDtoList;
    }

    @Override
    public List<AlumnoDto> listAlumnosByClase(ClaseDto claseDto) {
        List<AlumnoDto> alumnoDtoList = new ArrayList<>();
        Clase clase = claseConverter.model2entity(claseDto);

        alumnoClaseJpaRepository.findAllByClase(clase).stream().forEach((alClas) -> {
            alumnoDtoList.add(alumnoConverter.entity2Model(alClas.getAlumno()));
        });

        return alumnoDtoList;
    }

    @Override
    public ClaseDto getClaseByAlumno(AlumnoDto alumnoDto) {
        AlumnoClase alumnoClase = alumnoClaseJpaRepository.findByAlumno_Id(alumnoDto.getId());
        return claseConverter.entity2model(alumnoClase.getClase());
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
    public List<AlumnoClaseDto> listAllAlumnoClaseByClase(ClaseDto claseDto) {
        List<AlumnoClaseDto> alumnoClaseDtoList = new ArrayList<>();
        Clase clase = claseConverter.model2entity(claseDto);

        alumnoClaseJpaRepository.findAllByClase(clase).stream().forEach(e -> {
            alumnoClaseDtoList.add(alumnoClaseConverter.entity2model(e));
        });

        return alumnoClaseDtoList;
    }

    @Override
    public int getNumeroAlumnosByClase(ClaseDto claseDto) {
        return alumnoClaseJpaRepository.findAllByClase(claseConverter.model2entity(claseDto)).size();
    }

    @Override
    public void removeAllAlumnosByClase(ClaseDto claseDto) {
        alumnoClaseJpaRepository.removeAllByClase(claseConverter.model2entity(claseDto));
    }

    @Override
    public void removeAllAlumnosByCentro(CentroDto centroDto) {
        List<ClaseDto> claseDtoList = claseService.listClases();

        claseDtoList.stream().forEach(clase -> {
            if (clase.getCentro().equals(centroDto)) {
                alumnoClaseJpaRepository.removeAllByClase(claseConverter.model2entity(clase));
            }
        });
    }

    @Override
    public void updateListaAlumnos(ListaClaseDto listaClaseDto) {
        List<Integer> idAlumnos = listaClaseDto.getIdAlumnoList();
        List<AlumnoClaseDto> alumnoClaseDtoList = this.listAllAlumnoClaseByClase(listaClaseDto.getClaseDto());
        List<AlumnoDto> alumnoListaNuevaDto = new ArrayList<>();
        List<AlumnoDto> alumnoListaAntiguaDto = new ArrayList<>();

        idAlumnos.stream().forEach(id -> {
            alumnoListaNuevaDto.add(alumnoService.getAlumnoById(id));
        });

        alumnoClaseDtoList.stream().forEach(alumnoClaseDto -> {
            alumnoListaAntiguaDto.add(alumnoClaseDto.getAlumno());
        });

        alumnoListaNuevaDto.stream().forEach(alumnoDto -> {
            if(!alumnoListaAntiguaDto.contains(alumnoDto)){
                this.addAlumnoLista(alumnoDto, listaClaseDto.getClaseDto());
            } else {
                alumnoListaAntiguaDto.remove(alumnoDto);
            }
        });

        alumnoListaAntiguaDto.stream().forEach(alumnoDto -> {
            this.removeAlumnoCentro(alumnoDto);
        });

    }

}