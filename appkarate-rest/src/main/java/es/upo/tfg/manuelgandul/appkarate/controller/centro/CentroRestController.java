package es.upo.tfg.manuelgandul.appkarate.controller.centro;

import es.upo.tfg.manuelgandul.appkarate.controller.father.Api;
import es.upo.tfg.manuelgandul.appkarate.model.centro.CentroDto;
import es.upo.tfg.manuelgandul.appkarate.service.centro.CentroService;
import es.upo.tfg.manuelgandul.appkarate.webservicedto.common.IdToken;
import es.upo.tfg.manuelgandul.appkarate.webservicedto.common.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centros")
public class CentroRestController extends Api {

    @Autowired
    @Qualifier("centroService")
    private CentroService centroService;

    @Override
    @PostMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CentroDto> get(@RequestBody IdToken idToken) {
        ResponseEntity<CentroDto> centroDtoResponseEntity;

        if (super.isLoged(idToken.getUser(), idToken.getToken())) {
            CentroDto centroDto = centroService.getCentroById(idToken.getId());

            centroDtoResponseEntity = new ResponseEntity<>(centroDto, HttpStatus.OK);
        } else {
            centroDtoResponseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return centroDtoResponseEntity;
    }

    @Override
    @PostMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CentroDto>> list(@RequestBody Token token) {
        ResponseEntity<List<CentroDto>> listResponseEntity;

        if (super.isLoged(token.getUser(), token.getToken())) {
            List<CentroDto> centroDtoList = centroService.listCentros();

            listResponseEntity = new ResponseEntity<>(centroDtoList, HttpStatus.OK);
        } else {
            listResponseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return listResponseEntity;
    }
}
