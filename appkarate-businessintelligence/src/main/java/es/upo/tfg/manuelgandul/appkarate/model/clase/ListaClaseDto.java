package es.upo.tfg.manuelgandul.appkarate.model.clase;

import es.upo.tfg.manuelgandul.appkarate.model.Dto;

import java.util.ArrayList;
import java.util.List;

public class ListaClaseDto extends Dto {

    private ClaseDto claseDto;
    private List<Integer> idAlumnoList;

    public ListaClaseDto(){
    }

    public ListaClaseDto(ClaseDto claseDto){
        this.claseDto = claseDto;
        this.idAlumnoList = new ArrayList<>();
    }

    public ClaseDto getClaseDto() {
        return claseDto;
    }

    public void setClaseDto(ClaseDto claseDto) {
        this.claseDto = claseDto;
    }

    public List<Integer> getIdAlumnoList() {
        return idAlumnoList;
    }

    public void setIdAlumnoList(List<Integer> idAlumnoList) {
        this.idAlumnoList = idAlumnoList;
    }
}
