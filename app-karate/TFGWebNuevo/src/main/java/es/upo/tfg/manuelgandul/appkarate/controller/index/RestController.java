package es.upo.tfg.manuelgandul.appkarate.controller.index;

import es.upo.tfg.manuelgandul.appkarate.controller.father.Api;
import es.upo.tfg.manuelgandul.appkarate.model.Dto;
import es.upo.tfg.manuelgandul.appkarate.model.empleado.EmpleadoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController extends Api {

    @Override
    public ResponseEntity<Dto> get(int id, String user, String token) {
        return null;
    }

    @Override
    public ResponseEntity<List> list(String user, String token) {
        return null;
    }

    @GetMapping("/checkrest")
    public ResponseEntity<String> checkRest() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @GetMapping("/loginRest")
    public ResponseEntity<EmpleadoDto> loginRestMethod(@RequestParam(name = "user") String user,
                                                       @RequestParam(name = "password") String password) {
        ResponseEntity<EmpleadoDto> loginResponseEntity;
        EmpleadoDto empleadoDto = empleadoService.loginRest(user, password);

        if (null != empleadoDto && empleadoDto.getTipoUsuario().equalsIgnoreCase("Profesor")
                && empleadoDto.getActivo().equalsIgnoreCase("Activo")){

            empleadoDto.setToken(generateToken(empleadoDto));

            empleadoService.updateEmpleado(empleadoDto);
            empleadoDto.setContrasenya("");

            loginResponseEntity = new ResponseEntity<>(empleadoDto, HttpStatus.OK);
        } else {
            loginResponseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }


        return loginResponseEntity;
    }

    @GetMapping("/logoutRest")
    public ResponseEntity<Boolean> logOutRestMethod(@RequestParam(name = "user") String user,
                                                    @RequestParam(name = "token") String token){
        ResponseEntity<Boolean> logOutResponseEntity;
        EmpleadoDto empleadoDto = empleadoService.getEmpleadoByDni(user);

        if(null != empleadoDto && empleadoDto.getToken().equals(token)){
            empleadoDto.setToken("");
            empleadoService.updateEmpleado(empleadoDto);
            logOutResponseEntity = new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            logOutResponseEntity = new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        return  logOutResponseEntity;
    }

    private String generateToken(EmpleadoDto empleadoDto){
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        String str = empleadoDto.getId() + empleadoDto.getDni() + empleadoDto.getTipoUsuario();

        return pe.encode(str);
    }

}
