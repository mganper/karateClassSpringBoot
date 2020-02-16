package es.upo.tfg.manuelgandul.appkarate.controller;

import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.ObservacionDto;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.PagoDto;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.AlumnoService;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.ObservacionService;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.PagoService;
import es.upo.tfg.manuelgandul.appkarate.service.common.CinturonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    @Qualifier("alumnoService")
    private AlumnoService alumnoService;

    @Autowired
    @Qualifier("observacionService")
    private ObservacionService observacionService;

    @Autowired
    @Qualifier("pagoService")
    private PagoService pagoService;

    @Autowired
    @Qualifier("cinturonService")
    private CinturonService cinturonService;

    @GetMapping("/")
    public String redirect(){
        return "redirect:/alumno/alumnos";
    }

    @PostMapping("/saveAlumno")
    public String addAlumnoMethod(@Valid @ModelAttribute("alumno") AlumnoDto alumnoDto) {
        alumnoService.addAlumno(alumnoDto);
        return "redirect:/alumno?id=" + alumnoDto.getId();
    }

    @GetMapping("/alumno")
    public ModelAndView viewAlumno(@RequestParam(value = "id") int id){
        ModelAndView mav = new ModelAndView("alumno/alumno");

        AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);
        List<ObservacionDto> observacionDtoList = observacionService.listObservacionAlumno(alumnoDto);
        List<PagoDto> pagoDtoList = pagoService.listPagosAlumno(alumnoDto);

        mav.addObject("alumno", alumnoDto);
        mav.addObject("listaObservaciones", observacionDtoList);
        mav.addObject("listaPagos", pagoDtoList);

        return  mav;
    }

    @GetMapping("/addObservacion")
    public ModelAndView addObservacion(@RequestParam(value = "id") int id){
        ModelAndView mav = new ModelAndView("/alumno/crearObservacion");

        AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);
        mav.addObject("alumno", alumnoDto);

        return mav;
    }

    @GetMapping("/removeObservacion")
    public String removeObservacion(@RequestParam(value = "id") int id){
        ObservacionDto observacionDto = observacionService.getObservacionById(id);
        observacionService.removeObservacion(observacionDto);

        return "redirect:/alumno?id=" + observacionDto.getAlumno().getId();
    }

    @GetMapping("/removePago")
    public String removePago(@RequestParam(value = "id") int id){
        PagoDto pagoDto = pagoService.getPagoById(id);
        pagoService.removePago(pagoDto);

        return "redirect:/alumno?id=" + pagoDto.getAlumno().getId();
    }

    @GetMapping("/alumnos")
    public String getAlumnos(Model model) {
        cinturonService.comprobarCinturones();

        model.addAttribute("alumnos", alumnoService.listAllAlumnos());
        return "alumno/alumnos";
    }

    @GetMapping("/addAlumno")
    public ModelAndView addAlumno() {
        cinturonService.comprobarCinturones();

        ModelAndView mav = new ModelAndView("alumno/crearAlumno");

        mav.addObject("alumno", new AlumnoDto());
        mav.addObject("cinturones", cinturonService.listCinturon());

        return mav;
    }
}
