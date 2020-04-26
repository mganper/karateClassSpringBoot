package es.upo.tfg.manuelgandul.appkarate.controller.alumno;

import es.upo.tfg.manuelgandul.appkarate.controller.father.Api;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.PagoDto;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.AlumnoService;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.ObservacionService;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Override
    @GetMapping("/get")
    public ResponseEntity<AlumnoDto> get(@RequestParam(name = "id") int id,
                                         @RequestParam(name = "user") String user,
                                         @RequestParam(name = "token") String token) {
        ResponseEntity<AlumnoDto> alumnoDtoResponseEntity;

        if (super.isLoged(user, token)) {
            AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);
            alumnoDto.setObservacionDtoList(observacionService.listObservacionAlumno(alumnoDto));

            alumnoDtoResponseEntity = new ResponseEntity<>(alumnoDto, HttpStatus.OK);
        } else {
            alumnoDtoResponseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return alumnoDtoResponseEntity;
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<List<AlumnoDto>> list(@RequestParam(name = "user") String user,
                                                @RequestParam(name = "token") String token) {
        ResponseEntity<List<AlumnoDto>> listResponseEntity;

        if (super.isLoged(user, token)) {
            List<AlumnoDto> alumnoDtoList = alumnoService.listAllAlumnos();

            listResponseEntity = new ResponseEntity<>(alumnoDtoList, HttpStatus.OK);
        } else {
            listResponseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return listResponseEntity;
    }

    @GetMapping("/addPago")
    public ResponseEntity<Boolean> addPagoMethod(@RequestParam(name = "id") int id,
                                                 @RequestParam(name = "mes") int mes,
                                                 @RequestParam(name = "cantidad") double cantidad,
                                                 @RequestParam(name = "user") String user,
                                                 @RequestParam(name = "token") String token) {
        ResponseEntity<Boolean> listResponseEntity;

        if (super.isLoged(user, token)) {
            AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);
            PagoDto pagoDto = new PagoDto();

            pagoDto.setAlumno(alumnoDto);
            pagoDto.setMesPagado(LocalDate.of(LocalDate.now().getYear(), mes, 1));
            pagoDto.setCantidad(cantidad);
            pagoDto.setFecha(LocalDate.now());

            pagoService.savePago(pagoDto);

            listResponseEntity = new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            listResponseEntity = new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }

        return listResponseEntity;
    }
}
