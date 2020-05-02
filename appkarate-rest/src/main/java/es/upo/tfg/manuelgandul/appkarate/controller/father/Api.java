package es.upo.tfg.manuelgandul.appkarate.controller.father;

import es.upo.tfg.manuelgandul.appkarate.model.Dto;
import es.upo.tfg.manuelgandul.appkarate.service.empleado.EmpleadoService;
import es.upo.tfg.manuelgandul.appkarate.webservicedto.common.IdToken;
import es.upo.tfg.manuelgandul.appkarate.webservicedto.common.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class Api <D extends Dto> {

    @Autowired
    @Qualifier("empleadoService")
    protected EmpleadoService empleadoService;

    public abstract ResponseEntity<D> get(IdToken idToken);

    public abstract ResponseEntity<List<D>> list(Token token);

    protected boolean isLoged(String user, String token){
        return empleadoService.isLogged(user, token);
    }

}
