package es.upo.tfg.manuelgandul.appkarate.controller;

import es.upo.tfg.manuelgandul.appkarate.service.centro.CentroService;
import es.upo.tfg.manuelgandul.appkarate.service.common.CinturonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/centro")
public class CentroController {

    @Autowired
    @Qualifier("centroService")
    private CentroService centroService;

    @Autowired
    @Qualifier("cinturonService")
    private CinturonService cinturonService;

    @GetMapping("/")
    public String redirect(){
        return "redirect:/centro/centros";
    }

    @GetMapping("/centros")
    public String getAlumnos(Model model) {
        cinturonService.comprobarCinturones();

        model.addAttribute("centros", centroService.listCentros());

        return "centro/centros";
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
