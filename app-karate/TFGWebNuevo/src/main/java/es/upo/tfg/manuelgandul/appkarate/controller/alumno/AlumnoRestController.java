package es.upo.tfg.manuelgandul.appkarate.controller.alumno;

import es.upo.tfg.manuelgandul.appkarate.controller.father.Api;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/alumnos")
public class AlumnoRestController extends Api {
    @Override
    public AlumnoDto get(int id) {
        AlumnoDto alumnoDto;

        return null;
    }

    @Override
    public List<AlumnoDto> list() {
        return null;
    }
}
