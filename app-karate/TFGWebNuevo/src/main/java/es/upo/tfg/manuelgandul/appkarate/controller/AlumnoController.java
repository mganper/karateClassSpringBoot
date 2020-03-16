package es.upo.tfg.manuelgandul.appkarate.controller;

import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.ObservacionDto;
import es.upo.tfg.manuelgandul.appkarate.model.alumno.PagoDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.FaltasDto;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.AlumnoService;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.ObservacionService;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.PagoService;
import es.upo.tfg.manuelgandul.appkarate.service.clase.ClaseService;
import es.upo.tfg.manuelgandul.appkarate.service.clase.FaltasService;
import es.upo.tfg.manuelgandul.appkarate.service.common.CinturonService;
import es.upo.tfg.manuelgandul.appkarate.service.relations.AlumnoClaseService;
import es.upo.tfg.manuelgandul.appkarate.utility.Utility;
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

    @Autowired
    @Qualifier("alumnoClaseService")
    private AlumnoClaseService alumnoClaseService;

    @Autowired
    @Qualifier("faltasService")
    private FaltasService faltasService;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/alumno/alumnos";
    }

    @GetMapping("/alumnos")
    public String getAlumnosMethod(Model model) {
        cinturonService.comprobarCinturones();

        model.addAttribute("alumnos", alumnoService.listAllAlumnos());
        return "alumno/alumnos";
    }

    @GetMapping("/alumno")
    public ModelAndView viewAlumnoMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav = new ModelAndView("alumno/alumno");

        AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);
        List<ObservacionDto> observacionDtoList = observacionService.listObservacionAlumno(alumnoDto);
        List<PagoDto> pagoDtoList = pagoService.listPagosAlumno(alumnoDto);

        ClaseDto claseDto = alumnoClaseService.getClaseByAlumno(alumnoDto);
        alumnoDto.setClaseDto(claseDto);

        mav.addObject("alumno", alumnoDto);
        mav.addObject("listaObservaciones", observacionDtoList);
        mav.addObject("listaPagos", pagoDtoList);

        return mav;
    }

    @GetMapping("/addAlumno")
    public ModelAndView addAlumnoMethod() {
        cinturonService.comprobarCinturones();

        ModelAndView mav = new ModelAndView("alumno/crearAlumno");
        AlumnoDto alumnoDto = new AlumnoDto();

        alumnoDto.setClaseDto(new ClaseDto());

        mav.addObject("alumno", alumnoDto);
        mav.addObject("cinturones", cinturonService.listCinturon());
        mav.addObject("clases", claseService.listClases());

        return mav;
    }

    @PostMapping("/saveAlumno")
    public String saveAlumnoMethod(@Valid @ModelAttribute("alumno") AlumnoDto alumnoDto) {
        ClaseDto claseDto = null;

        if (alumnoDto.getClaseDto().getId() != -1)
            claseDto = claseService.getClaseById(alumnoDto.getClaseDto().getId());

        alumnoDto.setFechaNac(Utility.stringToDate(alumnoDto.getFechaString()));
        alumnoDto = alumnoService.addAlumno(alumnoDto);

        if (claseDto != null)
            alumnoClaseService.addAlumnoLista(alumnoDto, claseDto);

        return "redirect:/alumno/alumno?id=" + alumnoDto.getId();
    }

    @GetMapping("/editAlumno")
    public ModelAndView updateAlumnoMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav = new ModelAndView("alumno/modificarAlumno");

        List<ClaseDto> claseDtoList = claseService.listClases();
        AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);

        mav.addObject("alumno", alumnoDto);
        mav.addObject("cinturones", cinturonService.listCinturon());
        mav.addObject("clases", claseDtoList);

        return mav;
    }

    @PostMapping("/saveUpdatedAlumno")
    public String saveUpdatedAlumnoMethod(@Valid @ModelAttribute("alumno") AlumnoDto alumnoDto) {
        ClaseDto claseDto = null;

        if (alumnoDto.getClaseDto().getId() != -1)
            claseDto = claseService.getClaseById(alumnoDto.getClaseDto().getId());

        if (claseDto != null) {
            alumnoClaseService.removeAlumnoCentro(alumnoDto);
            alumnoClaseService.addAlumnoLista(alumnoDto, claseDto);
        }

        if (alumnoDto.getFechaString() == null) {
            alumnoDto.setFechaNac(alumnoService.getAlumnoById(alumnoDto.getId()).getFechaNac());
        } else {
            alumnoDto.setFechaNac(Utility.stringToDate(alumnoDto.getFechaString()));
        }

        alumnoDto = alumnoService.addAlumno(alumnoDto);

        return "redirect:/alumno/alumno?id=" + alumnoDto.getId();
    }

    @GetMapping("/altaAlumno")
    public String altaAlumnoMethod(@RequestParam(value = "id") int id) {
        AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);

        alumnoDto.setActivo("Activo");
        alumnoService.updateAlumno(alumnoDto);

        return "redirect:/alumno/alumno?id=" + alumnoDto.getId();
    }

    @GetMapping("/bajaAlumno")
    public String bajaAlumnoMethod(@RequestParam(value = "id") int id) {
        AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);

        alumnoDto.setActivo("Inactivo");
        alumnoService.updateAlumno(alumnoDto);

        alumnoClaseService.removeAlumnoCentro(alumnoDto);

        return "redirect:/alumno/alumno?id=" + alumnoDto.getId();
    }

    @GetMapping("/addObservacion")
    public ModelAndView addObservacionMethod(@RequestParam(value = "id") int id) {
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
    public String saveAObservacionMethod(@Valid @ModelAttribute("observacion") ObservacionDto observacionDto) {
        observacionDto.setFecha(LocalDate.now());
        observacionDto.setAlumno(alumnoService.getAlumnoById(observacionDto.getIdAlumno()));

        observacionService.addObservacion(observacionDto);
        return "redirect:/alumno/alumno?id=" + observacionDto.getAlumno().getId();
    }

    @GetMapping("/removeObservacion")
    public String removeObservacionMethod(@RequestParam(value = "id") int id) {
        ObservacionDto observacionDto = observacionService.getObservacionById(id);
        observacionService.removeObservacion(observacionDto);

        return "redirect:/alumno/alumno?id=" + observacionDto.getAlumno().getId();
    }

    @GetMapping("/removePago")
    public String removePagoMethod(@RequestParam(value = "id") int id) {
        PagoDto pagoDto = pagoService.getPagoById(id);
        pagoService.removePago(pagoDto);

        return "redirect:/alumno/alumno?id=" + pagoDto.getAlumno().getId();
    }

    @GetMapping("/faltasAlumno")
    public ModelAndView viewFaltasMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav = new ModelAndView("alumno/faltasAlumno");

        AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);
        List<FaltasDto> faltasDtoList = faltasService.listFaltasByAlumno(alumnoDto);

        mav.addObject("faltas", faltasDtoList);
        mav.addObject("alumno", alumnoDto);

        return mav;
    }

}
