package es.upo.tfg.manuelgandul.appkarate.converter;

import es.upo.tfg.manuelgandul.appkarate.entity.Alumno;
import es.upo.tfg.manuelgandul.appkarate.model.AlumnoModel;
import org.springframework.stereotype.Component;

@Component("alumnoConverter")
public class AlumnoConverter {

    public AlumnoModel entity2Model(Alumno alumno){
        AlumnoModel alumnoModel = new AlumnoModel();

        alumnoModel.setDni(alumno.getDni());
        alumnoModel.setNombre(alumno.getNombre());
        alumnoModel.setApellidos(alumno.getApellidos());
        alumnoModel.setTlf(alumno.getTelefono_contacto());
        alumnoModel.setDir(alumno.getDireccion());

        return alumnoModel;
    }

    public Alumno model2Entity(AlumnoModel alumnoModel){
        Alumno alumno = new Alumno();

        alumno.setDni(alumnoModel.getDni());
        alumno.setNombre(alumnoModel.getNombre());
        alumno.setApellidos(alumnoModel.getApellidos());
        alumno.setTelefono_contacto(alumnoModel.getTlf());
        alumno.setDireccion(alumnoModel.getDir());

        return alumno;
    }
}
