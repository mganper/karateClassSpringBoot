package es.upo.tfg.manuelgandul.appkarate.controller.clase;

import es.upo.tfg.manuelgandul.appkarate.controller.father.Api;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.FaltasDto;
import es.upo.tfg.manuelgandul.appkarate.model.relations.AlumnoClaseDto;
import es.upo.tfg.manuelgandul.appkarate.service.clase.ClaseService;
import es.upo.tfg.manuelgandul.appkarate.service.clase.FaltasService;
import es.upo.tfg.manuelgandul.appkarate.service.relations.AlumnoClaseService;
import es.upo.tfg.manuelgandul.appkarate.webservicedto.common.IdToken;
import es.upo.tfg.manuelgandul.appkarate.webservicedto.common.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/clases")
public class ClaseRestController extends Api {

    @Autowired
    @Qualifier("claseService")
    private ClaseService claseService;

    @Autowired
    @Qualifier("faltasService")
    private FaltasService faltasService;

    @Autowired
    @Qualifier("alumnoClaseService")
    private AlumnoClaseService alumnoClaseService;

    @Override
    @PostMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClaseDto> get(@RequestBody IdToken idToken) {
        ResponseEntity<ClaseDto> claseDtoResponseEntity;

        if(super.isLoged(idToken.getUser(), idToken.getToken())){
            ClaseDto claseDto = claseService.getClaseById(idToken.getId());

            claseDtoResponseEntity = new ResponseEntity<>(claseDto, HttpStatus.OK);
        } else {
            claseDtoResponseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return claseDtoResponseEntity;
    }

    @Override
    @PostMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClaseDto>> list(@RequestBody Token token) {
        ResponseEntity<List<ClaseDto>> listResponseEntity;

        if(super.isLoged(token.getUser(), token.getToken())){
            List<ClaseDto> claseDtoList = claseService.listClases();

            listResponseEntity = new ResponseEntity<>(claseDtoList, HttpStatus.OK);
        } else {
            listResponseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return listResponseEntity;
    }

    @GetMapping("/listaClase")
    public ResponseEntity<List<AlumnoDto>> listaClaseMethod(@RequestParam(name = "id") int id,
                                                            @RequestParam(name = "user") String user,
                                                            @RequestParam(name = "token") String token){
        ResponseEntity<List<AlumnoDto>> listResponseEntity;

        if(super.isLoged(user, token)){
            ClaseDto claseDto = claseService.getClaseById(id);
            List<AlumnoDto> alumnoDtoList = alumnoClaseService.listAlumnosByClase(claseDto);

            listResponseEntity = new ResponseEntity<>(alumnoDtoList, HttpStatus.OK);
        } else {
            listResponseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return listResponseEntity;
    }

    @GetMapping("/setFaltas")
    public ResponseEntity<Boolean> setListaClase(@RequestParam(name = "id") int id,
                                                 @RequestParam(name = "faltas") List<AlumnoDto> alumnoDtoList,
                                                 @RequestParam(name = "user") String user,
                                                 @RequestParam(name = "token") String token){
        ResponseEntity<Boolean> booleanResponseEntity;

        if(super.isLoged(user, token)){
            List<AlumnoClaseDto> alumnoClaseDtoFaltalist = new ArrayList<>();

            alumnoDtoList.forEach(alumnoDto -> {
                alumnoClaseDtoFaltalist.add(alumnoClaseService.getAlumnoClaseByAlumno(alumnoDto));
            });

            alumnoClaseDtoFaltalist.forEach(alumnoClaseDto -> {
                FaltasDto faltasDto = new FaltasDto();
                faltasDto.setAlumnoClase(alumnoClaseDto);
                faltasDto.setFecha(LocalDate.now());

                faltasService.addFalta(faltasDto);
            });

            booleanResponseEntity = new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            booleanResponseEntity = new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }

        return booleanResponseEntity;
    }
}
