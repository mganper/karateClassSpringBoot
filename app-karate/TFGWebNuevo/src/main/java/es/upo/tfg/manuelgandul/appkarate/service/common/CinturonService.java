package es.upo.tfg.manuelgandul.appkarate.service.common;

import es.upo.tfg.manuelgandul.appkarate.model.common.CinturonDto;

import java.util.List;

public interface CinturonService {

    public List<CinturonDto> listCinturon();

    public CinturonDto addCinturon(CinturonDto cinturonDto);

    public void removeCinturon(CinturonDto cinturonDto);

    public CinturonDto updateCinturon(CinturonDto cinturonDto);

    public CinturonDto getCinturonById(int id);

    public void comprobarCinturones();

}
