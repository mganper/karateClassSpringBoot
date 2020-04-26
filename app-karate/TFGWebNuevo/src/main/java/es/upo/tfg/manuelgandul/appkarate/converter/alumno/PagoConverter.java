package es.upo.tfg.manuelgandul.appkarate.converter.alumno;

import es.upo.tfg.manuelgandul.appkarate.entity.alumno.Pago;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.PagoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("pagoConverter")
public class PagoConverter {

    @Autowired
    @Qualifier("alumnoConverter")
    private AlumnoConverter alumnoConverter;

    public PagoDto entity2Model(Pago pago){
        PagoDto pagoDto = null;

        if(null != pago) {
            pagoDto = new PagoDto();

            pagoDto.setId(pago.getId());
            pagoDto.setAlumno(alumnoConverter.entity2Model(pago.getAlumno()));
            pagoDto.setFecha(pago.getFecha());
            pagoDto.setMesPagado(pago.getMes_pagado());
            pagoDto.setCantidad(pago.getCantidad());
        }

        return pagoDto;
    }

    public Pago model2Entity(PagoDto pagoDto){
        Pago pago = null;

        if(null != pagoDto) {
            pago = new Pago();
            LocalDate mesPagado = pagoDto.getMesPagado().plusDays(1);
            LocalDate fechaPago = pagoDto.getFecha().plusDays(1);

            pago.setId(pagoDto.getId());
            pago.setAlumno(alumnoConverter.model2Entity(pagoDto.getAlumno()));
            pago.setFecha(fechaPago);
            pago.setMes_pagado(mesPagado);
            pago.setCantidad(pagoDto.getCantidad());
        }

        return pago;
    }

}
