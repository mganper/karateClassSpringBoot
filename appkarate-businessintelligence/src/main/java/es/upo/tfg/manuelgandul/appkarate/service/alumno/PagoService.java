package es.upo.tfg.manuelgandul.appkarate.service.alumno;

import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.PagoDto;

import java.util.List;

public interface PagoService {
    List<PagoDto> listPagosAlumno(AlumnoDto alumnoDto);

    void removePago(PagoDto pagoDto);

    PagoDto getPagoById(int id);

    PagoDto savePago(PagoDto pagoDto);
}
