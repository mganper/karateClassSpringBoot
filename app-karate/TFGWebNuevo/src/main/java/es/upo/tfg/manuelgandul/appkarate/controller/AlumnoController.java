package es.upo.tfg.manuelgandul.appkarate.controller;

import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.ObservacionDto;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.PagoDto;
import es.upo.tfg.manuelgandul.appkarate.model.common.CinturonDto;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.AlumnoService;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.ObservacionService;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.PagoService;
import es.upo.tfg.manuelgandul.appkarate.service.clase.ClaseService;
import es.upo.tfg.manuelgandul.appkarate.service.common.CinturonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
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

    @Autowired
    @Qualifier("claseService")
    private ClaseService claseService;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/alumno/alumnos";
    }

    @GetMapping("/alumnos")
    public String getAlumnos(Model model) {
        cinturonService.comprobarCinturones();

        model.addAttribute("alumnos", alumnoService.listAllAlumnos());
        return "alumno/alumnos";
    }

    @GetMapping("/alumno")
    public ModelAndView viewAlumno(@RequestParam(value = "id") int id) {
        ModelAndView mav = new ModelAndView("alumno/alumno");

        AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);
        List<ObservacionDto> observacionDtoList = observacionService.listObservacionAlumno(alumnoDto);
        List<PagoDto> pagoDtoList = pagoService.listPagosAlumno(alumnoDto);

        mav.addObject("alumno", alumnoDto);
        mav.addObject("listaObservaciones", observacionDtoList);
        mav.addObject("listaPagos", pagoDtoList);

        return mav;
    }

    @GetMapping("/addAlumno")
    public ModelAndView addAlumno() {
        cinturonService.comprobarCinturones();

        ModelAndView mav = new ModelAndView("alumno/crearAlumno");

        mav.addObject("alumno", new AlumnoDto());
        mav.addObject("cinturones", cinturonService.listCinturon());
        mav.addObject("clases", claseService.listClases());

        return mav;
    }

    @PostMapping("/saveAlumno")
    public String addAlumnoMethod(@Valid @ModelAttribute("alumno") AlumnoDto alumnoDto) {
        alumnoDto.fechaStringToDate();

        alumnoDto = alumnoService.addAlumno(alumnoDto);

        return "redirect:/alumno/alumno?id=" + alumnoDto.getId();
    }

    @GetMapping("/editAlumno")
    public ModelAndView updateAlumno(@RequestParam(value = "id") int id) {
        ModelAndView mav = new ModelAndView("alumno/modificarAlumno");

        mav.addObject("alumno", alumnoService.getAlumnoById(id));
        mav.addObject("cinturones", cinturonService.listCinturon());
        mav.addObject("clases", claseService.listClases());

        return mav;
    }

    @PostMapping("/saveUpdatedAlumno")
    public String saveUpdatedAlumnoMethod(@Valid @ModelAttribute("alumno") AlumnoDto alumnoDto) {

        if (alumnoDto.getFechaString() == null) {
            alumnoDto.setFechaNac(alumnoService.getAlumnoById(alumnoDto.getId()).getFechaNac());
        } else {
            alumnoDto.fechaStringToDate();
        }

        alumnoDto = alumnoService.addAlumno(alumnoDto);

        return "redirect:/alumno/alumno?id=" + alumnoDto.getId();
    }

    @GetMapping("/altaAlumno")
    public String altaAlumno(@RequestParam(value = "id") int id) {
        AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);

        alumnoDto.setActivo("Activo");
        alumnoService.updateAlumno(alumnoDto);

        return "redirect:/alumno/alumno?id=" + alumnoDto.getId();
    }

    @GetMapping("/bajaAlumno")
    public String bajaAlumno(@RequestParam(value = "id") int id) {
        AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);

        alumnoDto.setActivo("Inactivo");
        alumnoService.updateAlumno(alumnoDto);

        return "redirect:/alumno/alumno?id=" + alumnoDto.getId();
    }

    @GetMapping("/addObservacion")
    public ModelAndView addObservacion(@RequestParam(value = "id") int id) {
        ModelAndView mav = new ModelAndView("/alumno/crearObservacion");
        ObservacionDto observacionDto = new ObservacionDto();
        AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);

        observacionDto.setAlumno(alumnoDto);
        observacionDto.setIdAlumno(id);

        List<ObservacionDto> observacionDtoList = observacionService.listObservacionAlumno(alumnoDto);

        mav.addObject("observacion", observacionDto);
        mav.addObject("listaObservaciones", observacionDtoList);

        return mav;
    }

    @PostMapping("/saveObservacion")
    public String saveAObservacion(@Valid @ModelAttribute("observacion") ObservacionDto observacionDto) {
        observacionDto.setFecha(LocalDate.now());
        observacionDto.setAlumno(alumnoService.getAlumnoById(observacionDto.getIdAlumno()));

        observacionService.addObservacion(observacionDto);
        return "redirect:/alumno/alumno?id=" + observacionDto.getAlumno().getId();
    }

    @GetMapping("/removeObservacion")
    public String removeObservacion(@RequestParam(value = "id") int id) {
        ObservacionDto observacionDto = observacionService.getObservacionById(id);
        observacionService.removeObservacion(observacionDto);

        return "redirect:/alumno/alumno?id=" + observacionDto.getAlumno().getId();
    }

    @GetMapping("/removePago")
    public String removePago(@RequestParam(value = "id") int id) {
        PagoDto pagoDto = pagoService.getPagoById(id);
        pagoService.removePago(pagoDto);

        return "redirect:/alumno/alumno?id=" + pagoDto.getAlumno().getId();
    }

    @GetMapping("/removeObs")
    public String removeObs(@RequestParam(value = "id") int id) {
        ObservacionDto observacionDto = observacionService.getObservacionById(id);

        observacionService.removeObservacion(observacionDto);

        return "redirect:/alumno/alumno?id=" + observacionDto.getAlumno().getId();
    }

    @GetMapping("/removePag")
    public String removePag(@RequestParam(value = "id") int id) {
        PagoDto pagoDto = pagoService.getPagoById(id);

        pagoService.removePago(pagoDto);

        return "redirect:/alumno/alumno?id=" + pagoDto.getAlumno().getId();
    }
}
