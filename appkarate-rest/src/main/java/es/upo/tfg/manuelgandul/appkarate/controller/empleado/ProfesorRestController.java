package es.upo.tfg.manuelgandul.appkarate.controller.empleado;

import es.upo.tfg.manuelgandul.appkarate.controller.father.Api;
import es.upo.tfg.manuelgandul.appkarate.model.empleado.EmpleadoDto;
import es.upo.tfg.manuelgandul.appkarate.utility.Utility;
import es.upo.tfg.manuelgandul.appkarate.webservicedto.common.PasswordJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorRestController extends Api {

    @Override
    @GetMapping("/get")
    public ResponseEntity<EmpleadoDto> get(@RequestParam(name = "id") int id) {
        ResponseEntity<EmpleadoDto> empleadoDtoResponseEntity;

        if (super.isProfesor()) {
            EmpleadoDto empleadoDto = super.empleadoService.getEmpleadoById(id);
            empleadoDto.setContrasenya("");

            empleadoDtoResponseEntity = new ResponseEntity<>(empleadoDto, HttpStatus.OK);
        } else {
            empleadoDtoResponseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return empleadoDtoResponseEntity;
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<List<EmpleadoDto>> list() {
        ResponseEntity<List<EmpleadoDto>> listResponseEntity;

        if (super.isProfesor()) {
            List<EmpleadoDto> empleadoDtoList = super.empleadoService.listProfesores();

            empleadoDtoList.parallelStream().forEach(e -> {
                e.setContrasenya("");
            });

            listResponseEntity = new ResponseEntity<>(empleadoDtoList, HttpStatus.OK);
        } else {
            listResponseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return listResponseEntity;
    }

    @PutMapping("/changePassword")
    public ResponseEntity<Boolean> changePassword(@RequestBody PasswordJson passwordJson) {
        ResponseEntity<Boolean> booleanResponseEntity;

        if (super.isProfesor()) {
            EmpleadoDto empleadoDto = super.empleadoService.getEmpleadoByDni(empleadoService.getUserAuthenticated().getDni());

            if (Utility.matchPassword(passwordJson.getOldPassword(), empleadoDto.getContrasenya())) {
                empleadoDto.setContrasenya(Utility.passwordEncoder(passwordJson.getNewPassword()));
                super.empleadoService.updateEmpleado(empleadoDto);

                booleanResponseEntity = new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                booleanResponseEntity = new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } else {
            booleanResponseEntity = new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }

        return booleanResponseEntity;
    }
}
