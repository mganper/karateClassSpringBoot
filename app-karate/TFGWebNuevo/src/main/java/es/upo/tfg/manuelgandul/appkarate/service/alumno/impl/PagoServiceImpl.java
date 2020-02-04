package es.upo.tfg.manuelgandul.appkarate.service.alumno.impl;

import es.upo.tfg.manuelgandul.appkarate.converter.alumno.PagoConverter;
import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Pago;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.PagoDto;
import es.upo.tfg.manuelgandul.appkarate.repository.alumno.PagoJpaRepository;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("pagoService")
public class PagoServiceImpl implements PagoService {

    @Autowired
    @Qualifier("pagoJpaRepository")
    private PagoJpaRepository pagoJpaRepository;

    @Autowired
    @Qualifier("pagoConverter")
    private PagoConverter pagoConverter;

    @Override
    public List<PagoDto> listPagosAlumno(AlumnoDto alumnoDto) {
        List<PagoDto> pagoDtoList = new ArrayList<>();

        pagoJpaRepository.findAllByAlumno_IdOrderByFecha(alumnoDto.getId()).stream().forEach((pago) ->{
            pagoDtoList.add(pagoConverter.entity2Model(pago));
        });

        return pagoDtoList;
    }

    @Override
    public void removePago(PagoDto pagoDto) {
        pagoJpaRepository.delete(pagoConverter.model2Entity(pagoDto));
    }

    @Override
    public PagoDto getPagoById(int id) {
        Pago pago = pagoJpaRepository.findById(id);

        return pagoConverter.entity2Model(pago);
    }
}
