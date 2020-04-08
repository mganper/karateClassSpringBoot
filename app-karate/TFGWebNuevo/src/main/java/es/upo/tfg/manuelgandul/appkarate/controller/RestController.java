package es.upo.tfg.manuelgandul.appkarate.controller;

import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @GetMapping("/checkrest")
    public ResponseEntity<AlumnoDto> checkRest(){

        AlumnoDto alumnoDto = new AlumnoDto();
        return new ResponseEntity<AlumnoDto>(alumnoDto, HttpStatus.OK);
    }
}
