package es.upo.tfg.manuelgandul.appkarate.controller.empleado;

import es.upo.tfg.manuelgandul.appkarate.controller.father.Api;
import es.upo.tfg.manuelgandul.appkarate.model.empleado.EmpleadoDto;
import es.upo.tfg.manuelgandul.appkarate.utility.Utility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorRestController extends Api {

    @Override
    @GetMapping("/get")
    public ResponseEntity<EmpleadoDto> get(@RequestParam(name = "id") int id,
                                           @RequestParam(name = "user") String user,
                                           @RequestParam(name = "token") String token) {
        ResponseEntity<EmpleadoDto> empleadoDtoResponseEntity;

        if (super.isLoged(user, token)) {
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
    public ResponseEntity<List<EmpleadoDto>> list(@RequestParam(name = "user") String user,
                                                  @RequestParam(name = "token") String token) {
        ResponseEntity<List<EmpleadoDto>> listResponseEntity;

        if (super.isLoged(user, token)) {
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

    @GetMapping("/changePassword")
    public ResponseEntity<Boolean> changePassword(@RequestParam(name = "oldPassword") String oldPassword,
                                                  @RequestParam(name = "newPassword") String newPassword,
                                                  @RequestParam(name = "user") String user,
                                                  @RequestParam(name = "token") String token) {
        ResponseEntity<Boolean> booleanResponseEntity;

        if (super.isLoged(user, token)) {
            EmpleadoDto empleadoDto = super.empleadoService.getEmpleadoByDni(user);

            if(Utility.matchPassword(oldPassword, empleadoDto.getContrasenya())){
                empleadoDto.setContrasenya(Utility.passwordEncoder(newPassword));
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
