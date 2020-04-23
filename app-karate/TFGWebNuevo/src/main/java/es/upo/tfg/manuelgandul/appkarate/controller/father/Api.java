package es.upo.tfg.manuelgandul.appkarate.controller.father;

import es.upo.tfg.manuelgandul.appkarate.model.Dto;
import es.upo.tfg.manuelgandul.appkarate.service.empleado.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public abstract class Api <D extends Dto> {

    @Autowired
    @Qualifier("empleadoService")
    protected EmpleadoService empleadoService;

    public abstract D get(@RequestParam(name = "id") int id);

    public abstract List<D> list();

    protected boolean isLoged(String user, String token){
        return empleadoService.isLogged(user, token);
    }

}
