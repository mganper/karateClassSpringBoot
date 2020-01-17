package es.upo.tfg.manuelgandul.appkarate.service.alumno;

import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.PagoDto;

import java.util.List;

public interface PagoService {
    public List<PagoDto> listPagosAlumno(AlumnoDto alumnoDto);

    public void removePago(PagoDto pagoDto);
}
