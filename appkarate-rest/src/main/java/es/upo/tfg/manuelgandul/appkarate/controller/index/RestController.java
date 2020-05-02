package es.upo.tfg.manuelgandul.appkarate.controller.index;

import es.upo.tfg.manuelgandul.appkarate.controller.father.Api;
import es.upo.tfg.manuelgandul.appkarate.model.Dto;
import es.upo.tfg.manuelgandul.appkarate.model.empleado.EmpleadoDto;
import es.upo.tfg.manuelgandul.appkarate.webservicedto.common.IdToken;
import es.upo.tfg.manuelgandul.appkarate.webservicedto.common.Token;
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
    public ResponseEntity get(int id) {
        return null;
    }

    @Override
    public ResponseEntity<List> list() {
        return null;
    }

    @GetMapping("/checkrest")
    public ResponseEntity<String> checkRest() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<Boolean> loginRestMethod() {
        ResponseEntity<Boolean> loginResponseEntity;

        if (super.isProfesor()){
            loginResponseEntity = new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            loginResponseEntity = new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }

        return loginResponseEntity;
    }

}
