package es.upo.tfg.manuelgandul.appkarate.converter.alumno;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Pago;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.PagoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("pagoConverter")
public class PagoConverter {

    @Autowired
    @Qualifier("alumnoConverter")
    private AlumnoConverter alumnoConverter;

    public PagoDto entity2Model(Pago pago){
        PagoDto pagoDto = new PagoDto();

        pagoDto.setId(pago.getId());
        pagoDto.setAlumno(alumnoConverter.entity2Model(pago.getAlumno()));
        pagoDto.setFecha(pago.getFecha());
        pagoDto.setMesPagado(pago.getMes_pagado());
        pagoDto.setCantidad(pago.getCantidad());

        return pagoDto;
    }

    public Pago model2Entity(PagoDto pagoDto){
        Pago pago = new Pago();

        pago.setId(pagoDto.getId());
        pago.setAlumno(alumnoConverter.model2Entity(pagoDto.getAlumno()));
        pago.setFecha(pagoDto.getFecha());
        pago.setMes_pagado(pagoDto.getMesPagado());
        pago.setCantidad(pagoDto.getCantidad());

        return pago;
    }

}
