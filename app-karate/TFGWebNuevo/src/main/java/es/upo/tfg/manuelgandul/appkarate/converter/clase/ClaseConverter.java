package es.upo.tfg.manuelgandul.appkarate.converter.clase;

import es.upo.tfg.manuelgandul.appkarate.converter.centro.CentroConverter;
import es.upo.tfg.manuelgandul.appkarate.converter.empleado.EmpleadoConverter;
import es.upo.tfg.manuelgandul.appkarate.entity.clase.Clase;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("claseConverter")
public class ClaseConverter {

    @Autowired
    @Qualifier("centroConverter")
    private CentroConverter centroConverter;

    @Autowired
    @Qualifier("empleadoConverter")
    private EmpleadoConverter empleadoConverter;

    public ClaseDto entity2model(Clase clase){
        ClaseDto claseDto = new ClaseDto();

        claseDto.setId(clase.getId());
        claseDto.setHoraInicio(clase.getHora_inicio());
        claseDto.setHoraFin(clase.getHora_fin());
        claseDto.setEdadMaxima(clase.getEdad_maxima());
        claseDto.setEdadMinima(clase.getEdad_minima());
        claseDto.setMaxAlumnos(clase.getMax_alumnos());
        claseDto.setPrecio(clase.getPrecio());
        claseDto.setProfesor(empleadoConverter.entity2model(clase.getProfesor()));
        claseDto.setCentro(centroConverter.entity2model(clase.getCentro()));

        String activo = (clase.isActivo()) ? "Activo" : "Inactivo";

        claseDto.setActivo(activo);

        return claseDto;
    }

    public Clase model2entity(ClaseDto claseDto){
        Clase clase = new Clase();

        clase.setId(claseDto.getId());
        clase.setHora_inicio(claseDto.getHoraInicio());
        clase.setHora_fin(claseDto.getHoraFin());
        clase.setEdad_maxima(claseDto.getEdadMaxima());
        clase.setEdad_minima(claseDto.getEdadMinima());
        clase.setMax_alumnos(claseDto.getMaxAlumnos());
        clase.setPrecio(claseDto.getPrecio());
        clase.setProfesor(empleadoConverter.model2entity(claseDto.getProfesor()));
        clase.setCentro(centroConverter.model2entity(claseDto.getCentro()));

        Boolean activo = (claseDto.getActivo() == null || claseDto.getActivo().equals("Activo")) ? true : false;

        clase.setActivo(activo);

        return clase;
    }
}
