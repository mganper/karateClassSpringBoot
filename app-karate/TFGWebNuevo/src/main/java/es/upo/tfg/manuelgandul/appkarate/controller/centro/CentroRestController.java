package es.upo.tfg.manuelgandul.appkarate.controller.centro;

import es.upo.tfg.manuelgandul.appkarate.controller.father.Api;
import es.upo.tfg.manuelgandul.appkarate.model.centro.CentroDto;
import es.upo.tfg.manuelgandul.appkarate.service.centro.CentroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/centros")
public class CentroRestController extends Api {

    @Autowired
    @Qualifier("centroService")
    private CentroService centroService;

    @Override
    @GetMapping("/get")
    public ResponseEntity<CentroDto> get(@RequestParam(name = "id") int id,
                                         @RequestParam(name = "user") String user,
                                         @RequestParam(name = "token") String token) {
        ResponseEntity<CentroDto> centroDtoResponseEntity;

        if (super.isLoged(user, token)) {
            CentroDto centroDto = centroService.getCentroById(id);

            centroDtoResponseEntity = new ResponseEntity<>(centroDto, HttpStatus.OK);
        } else {
            centroDtoResponseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return centroDtoResponseEntity;
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<List<CentroDto>> list(@RequestParam(name = "user") String user,
                                                @RequestParam(name = "token") String token) {
        ResponseEntity<List<CentroDto>> listResponseEntity;

        if (super.isLoged(user, token)) {
            List<CentroDto> centroDtoList = centroService.listCentros();

            listResponseEntity = new ResponseEntity<>(centroDtoList, HttpStatus.OK);
        } else {
            listResponseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return listResponseEntity;
    }
}
