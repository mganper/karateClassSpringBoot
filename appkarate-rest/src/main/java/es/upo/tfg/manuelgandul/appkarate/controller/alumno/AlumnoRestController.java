package es.upo.tfg.manuelgandul.appkarate.controller.alumno;

import es.upo.tfg.manuelgandul.appkarate.controller.father.Api;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.ObservacionDto;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.PagoDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.AlumnoService;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.ObservacionService;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.PagoService;
import es.upo.tfg.manuelgandul.appkarate.service.relations.AlumnoClaseService;
import es.upo.tfg.manuelgandul.appkarate.webservicedto.common.PagoJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoRestController extends Api {

    @Autowired
    @Qualifier("alumnoService")
    private AlumnoService alumnoService;

    @Autowired
    @Qualifier("observacionService")
    private ObservacionService observacionService;

    @Autowired
    @Qualifier("pagoService")
    private PagoService pagoService;

    @Autowired
    @Qualifier("alumnoClaseService")
    private AlumnoClaseService alumnoClaseService;

    @Override
    @GetMapping("/get")
    public ResponseEntity<AlumnoDto> get(@RequestParam(name = "id") int id) {
        ResponseEntity<AlumnoDto> alumnoDtoResponseEntity;

        if (super.isProfesor()) {
            AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);
            List<ObservacionDto> observacionList = observacionService.listObservacionAlumno(alumnoDto);
            ClaseDto claseDto = alumnoClaseService.getClaseByAlumno(alumnoDto);

            observacionList.forEach(observacionDto -> {
                observacionDto.setAlumno(null);
            });

            alumnoDto.setObservacionDtoList(observacionList);
            alumnoDto.setClaseDto(claseDto);

            alumnoDtoResponseEntity = new ResponseEntity<>(alumnoDto, HttpStatus.OK);
        } else {
            alumnoDtoResponseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return alumnoDtoResponseEntity;
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<List<AlumnoDto>> list() {
        ResponseEntity<List<AlumnoDto>> listResponseEntity;

        if (super.isProfesor()) {
            List<AlumnoDto> alumnoDtoList = alumnoService.listAllAlumnos();

            alumnoDtoList.forEach(alumnoDto -> {
                ClaseDto claseDto = alumnoClaseService.getClaseByAlumno(alumnoDto);

                alumnoDto.setClaseDto(claseDto);
            });

            listResponseEntity = new ResponseEntity<>(alumnoDtoList, HttpStatus.OK);
        } else {
            listResponseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return listResponseEntity;
    }

    @PutMapping("/addPago")
    public ResponseEntity<Boolean> addPagoMethod(@RequestBody PagoJson pagoJson) {
        ResponseEntity<Boolean> listResponseEntity;

        if (super.isProfesor()) {
            AlumnoDto alumnoDto = alumnoService.getAlumnoById(pagoJson.getId());
            PagoDto pagoDto = new PagoDto();

            pagoDto.setAlumno(alumnoDto);
            pagoDto.setMesPagado(LocalDate.of(pagoJson.getAnyo(), pagoJson.getMes(), 1));
            pagoDto.setCantidad(pagoJson.getCantidad());
            pagoDto.setFecha(LocalDate.now());

            pagoService.savePago(pagoDto);

            listResponseEntity = new ResponseEntity<>(true, HttpStatus.CREATED);
        } else {
            listResponseEntity = new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }

        return listResponseEntity;
    }
}
