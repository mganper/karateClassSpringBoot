package es.upo.tfg.manuelgandul.appkarate.converter.alumno;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Pago;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.PagoDto;
import org.springframework.stereotype.Component;

@Component("pagoConverter")
public class PagoConverter {

    public PagoDto entity2Model(Pago pago){
        PagoDto pagoDto = new PagoDto();

        pagoDto.setId(pago.getId());
        pagoDto.setAlumno(pago.getAlumno());
        pagoDto.setFecha(pago.getFecha());
        pagoDto.setMesPagado(pago.getMes_pagado());

        return pagoDto;
    }

    public Pago model2Entity(PagoDto pagoDto){
        Pago pago = new Pago();

        pago.setId(pagoDto.getId());
        pago.setAlumno(pagoDto.getAlumno());
        pago.setFecha(pagoDto.getFecha());
        pago.setMes_pagado(pagoDto.getMesPagado());

        return pago;
    }

}
