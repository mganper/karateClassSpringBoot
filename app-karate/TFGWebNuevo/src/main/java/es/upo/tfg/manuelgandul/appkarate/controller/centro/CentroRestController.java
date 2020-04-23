package es.upo.tfg.manuelgandul.appkarate.controller.centro;

import es.upo.tfg.manuelgandul.appkarate.controller.father.Api;
import es.upo.tfg.manuelgandul.appkarate.model.Dto;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/centro")
public class CentroRestController extends Api {
    @Override
    public Dto get(int id) {
        return null;
    }

    @Override
    public List list() {
        return null;
    }
}
