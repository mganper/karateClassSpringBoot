package es.upo.tfg.manuelgandul.appkarate.controller.empleado;

import es.upo.tfg.manuelgandul.appkarate.controller.father.Api;
import es.upo.tfg.manuelgandul.appkarate.model.empleado.EmpleadoDto;
import es.upo.tfg.manuelgandul.appkarate.utility.Utility;
import es.upo.tfg.manuelgandul.appkarate.webservicedto.common.IdToken;
import es.upo.tfg.manuelgandul.appkarate.webservicedto.common.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorRestController extends Api {

    @Override
    @GetMapping("/get")
    public ResponseEntity<EmpleadoDto> get(@RequestBody IdToken idToken) {
        ResponseEntity<EmpleadoDto> empleadoDtoResponseEntity;

        if (super.isLoged(idToken.getUser(), idToken.getToken())) {
            EmpleadoDto empleadoDto = super.empleadoService.getEmpleadoById(idToken.getId());
            empleadoDto.setContrasenya("");

            empleadoDtoResponseEntity = new ResponseEntity<>(empleadoDto, HttpStatus.OK);
        } else {
            empleadoDtoResponseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return empleadoDtoResponseEntity;
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<List<EmpleadoDto>> list(@RequestBody Token token) {
        ResponseEntity<List<EmpleadoDto>> listResponseEntity;

        if (super.isLoged(token.getUser(), token.getToken())) {
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
