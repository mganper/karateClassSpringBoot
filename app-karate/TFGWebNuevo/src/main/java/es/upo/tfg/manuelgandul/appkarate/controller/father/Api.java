package es.upo.tfg.manuelgandul.appkarate.controller.father;

import es.upo.tfg.manuelgandul.appkarate.model.Dto;
import es.upo.tfg.manuelgandul.appkarate.service.empleado.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class Api <D extends Dto> {

    @Autowired
    @Qualifier("empleadoService")
    protected EmpleadoService empleadoService;

    public abstract ResponseEntity<D> get(int id, String user, String token);

    public abstract ResponseEntity<List<D>> list(String user, String token);

    protected boolean isLoged(String user, String token){
        return empleadoService.isLogged(user, token);
    }

}
