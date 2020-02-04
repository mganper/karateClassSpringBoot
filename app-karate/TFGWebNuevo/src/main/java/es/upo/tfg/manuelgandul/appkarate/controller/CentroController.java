package es.upo.tfg.manuelgandul.appkarate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/centro")
public class CentroController {

    @GetMapping("/")
    public String redirect(){
        return "redirect: /centro/centro";
    }

    @GetMapping("/centro")
    public ModelAndView viewCentro(/*@RequestParam(value = "id") int id*/){
        ModelAndView mav = new ModelAndView("centro/centro");

//        AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);
//        List<ObservacionDto> observacionDtoList = observacionService.listObservacionAlumno(alumnoDto);
//        List<PagoDto> pagoDtoList = pagoService.listPagosAlumno(alumnoDto);
//
//        mav.addObject("alumno", alumnoDto);
//        mav.addObject("listaObservaciones", observacionDtoList);
//        mav.addObject("listaPagos", pagoDtoList);

        return  mav;
    }
}
