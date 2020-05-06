package es.upo.tfg.manuelgandul.appkarate.controller.clase;

import es.upo.tfg.manuelgandul.appkarate.controller.father.Api;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.FaltasDto;
import es.upo.tfg.manuelgandul.appkarate.model.relations.AlumnoClaseDto;
import es.upo.tfg.manuelgandul.appkarate.service.clase.ClaseService;
import es.upo.tfg.manuelgandul.appkarate.service.clase.FaltasService;
import es.upo.tfg.manuelgandul.appkarate.service.relations.AlumnoClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
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
    @GetMapping("/get")
    public ResponseEntity<ClaseDto> get(@RequestParam(name = "id") int id) {
        ResponseEntity<ClaseDto> claseDtoResponseEntity;

        if(super.isProfesor()){
            ClaseDto claseDto = claseService.getClaseById(id);

            claseDtoResponseEntity = new ResponseEntity<>(claseDto, HttpStatus.OK);
        } else {
            claseDtoResponseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return claseDtoResponseEntity;
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<List<ClaseDto>> list() {
        ResponseEntity<List<ClaseDto>> listResponseEntity;

        if(super.isProfesor()){
            List<ClaseDto> claseDtoList = claseService.listClases();

            listResponseEntity = new ResponseEntity<>(claseDtoList, HttpStatus.OK);
        } else {
            listResponseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return listResponseEntity;
    }

    @GetMapping("/listaClase")
    public ResponseEntity<List<AlumnoDto>> listaClaseMethod(@RequestParam(name = "id") int id){
        ResponseEntity<List<AlumnoDto>> listResponseEntity;

        if(super.isProfesor()){
            ClaseDto claseDto = claseService.getClaseById(id);
            List<AlumnoDto> alumnoDtoList = alumnoClaseService.listAlumnosByClase(claseDto);

            listResponseEntity = new ResponseEntity<>(alumnoDtoList, HttpStatus.OK);
        } else {
            listResponseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return listResponseEntity;
    }

    @PutMapping("/setFaltas")
    public ResponseEntity<Boolean> setListaClase(@RequestBody List<Integer> idList){
        ResponseEntity<Boolean> booleanResponseEntity;

        if(super.isProfesor()){
            List<AlumnoClaseDto> alumnoClaseDtoFaltalist = new ArrayList<>();

            idList.forEach(id -> {
                alumnoClaseDtoFaltalist.add(alumnoClaseService.getAlumnoClaseByAlumnoId(id));
            });

            alumnoClaseDtoFaltalist.forEach(alumnoClaseDto -> {
                FaltasDto faltasDto = new FaltasDto();
                faltasDto.setAlumnoClase(alumnoClaseDto);
                faltasDto.setFecha(LocalDate.now());

                faltasService.addFalta(faltasDto);
            });

            booleanResponseEntity = new ResponseEntity<>(true, HttpStatus.CREATED);
        } else {
            booleanResponseEntity = new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }

        return booleanResponseEntity;
    }
}
