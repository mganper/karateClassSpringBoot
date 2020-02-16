package es.upo.tfg.manuelgandul.appkarate.service.empleado.impl;

import es.upo.tfg.manuelgandul.appkarate.converter.empleado.EmpleadoConverter;
import es.upo.tfg.manuelgandul.appkarate.model.empleado.EmpleadoDto;
import es.upo.tfg.manuelgandul.appkarate.repository.empleado.EmpleadoJpaRepository;
import es.upo.tfg.manuelgandul.appkarate.service.empleado.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("empleadoService")
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    @Qualifier("empleadoJpaRepository")
    private EmpleadoJpaRepository empleadoJpaRepository;

    @Autowired
    @Qualifier("empleadoConverter")
    private EmpleadoConverter empleadoConverter;

    @Override
    public List<EmpleadoDto> listEmpleados() {
        List<EmpleadoDto> empleadoDtoList = new ArrayList<>();

        empleadoJpaRepository.findAll().stream().forEach((empleado) -> {
            empleadoDtoList.add(empleadoConverter.entity2model(empleado));
        });

        return empleadoDtoList;
    }

    @Override
    public EmpleadoDto addEmpleado(EmpleadoDto empleadoDto) {
        empleadoJpaRepository.save(empleadoConverter.model2entity(empleadoDto));

        return empleadoDto;
    }

    @Override
    public void removeEmpleado(EmpleadoDto empleadoDto) {
        empleadoJpaRepository.delete(empleadoConverter.model2entity(empleadoDto));
    }

    @Override
    public EmpleadoDto updateEmpleado(EmpleadoDto empleadoDto) {
        empleadoJpaRepository.save(empleadoConverter.model2entity(empleadoDto));

        return empleadoDto;
    }

    @Override
    public EmpleadoDto getEmpleadoById(int id) {
        return empleadoConverter.entity2model(empleadoJpaRepository.findById(id));
    }
}
