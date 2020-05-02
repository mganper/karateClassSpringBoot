package es.upo.tfg.manuelgandul.appkarate.controller.alumno;

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
import es.upo.tfg.manuelgandul.appkarate.service.empleado.EmpleadoService;
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

    @Autowired
    @Qualifier("empleadoService")
    private EmpleadoService empleadoService;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/alumno/alumnos";
    }

    @GetMapping("/alumnos")
    public String getAlumnosMethod(Model model) {
        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            cinturonService.comprobarCinturones();

            model.addAttribute("alumnos", alumnoService.listAllAlumnos());
            model.addAttribute("usuario", empleadoService.getUserAuthenticated());

            return "alumno/alumnos";
        } else {
            return "error/403";
        }
    }

    @GetMapping("/alumno")
    public ModelAndView viewAlumnoMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav;
        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);

            if(null != alumnoDto){
                mav = new ModelAndView("alumno/alumno");

                List<ObservacionDto> observacionDtoList = observacionService.listObservacionAlumno(alumnoDto);
                List<PagoDto> pagoDtoList = pagoService.listPagosAlumno(alumnoDto);

                ClaseDto claseDto = alumnoClaseService.getClaseByAlumno(alumnoDto);
                alumnoDto.setClaseDto(claseDto);

                mav.addObject("alumno", alumnoDto);
                mav.addObject("listaObservaciones", observacionDtoList);
                mav.addObject("listaPagos", pagoDtoList);
                mav.addObject("usuario", empleadoService.getUserAuthenticated());
            } else {
                mav = new ModelAndView("error/404");
            }
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @GetMapping("/addAlumno")
    public ModelAndView addAlumnoMethod() {
        cinturonService.comprobarCinturones();

        ModelAndView mav;
        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            mav = new ModelAndView("alumno/crearAlumno");
            AlumnoDto alumnoDto = new AlumnoDto();

            alumnoDto.setClaseDto(new ClaseDto());

            mav.addObject("alumno", alumnoDto);
            mav.addObject("cinturones", cinturonService.listCinturon());
            mav.addObject("clases", claseService.listClases());
            mav.addObject("usuario", empleadoService.getUserAuthenticated());
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @PostMapping("/saveAlumno")
    public String saveAlumnoMethod(@Valid @ModelAttribute("alumno") AlumnoDto alumnoDto) {
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            ClaseDto claseDto = null;

            if (alumnoDto.getClaseDto().getId() != -1)
                claseDto = claseService.getClaseById(alumnoDto.getClaseDto().getId());

            alumnoDto.setFechaNac(Utility.stringToDate(alumnoDto.getFechaString()));
            alumnoDto = alumnoService.addAlumno(alumnoDto);

            if (claseDto != null)
                alumnoClaseService.addAlumnoLista(alumnoDto, claseDto);

            str = "redirect:/alumno/alumno?id=" + alumnoDto.getId();
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/editAlumno")
    public ModelAndView updateAlumnoMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav;
        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);

            if(null != alumnoDto){
                mav = new ModelAndView("alumno/modificarAlumno");

                List<ClaseDto> claseDtoList = claseService.listClases();

                mav.addObject("alumno", alumnoDto);
                mav.addObject("cinturones", cinturonService.listCinturon());
                mav.addObject("clases", claseDtoList);
                mav.addObject("usuario", empleadoService.getUserAuthenticated());
            } else {
                mav = new ModelAndView("error/404");
            }
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @PostMapping("/saveUpdatedAlumno")
    public String saveUpdatedAlumnoMethod(@Valid @ModelAttribute("alumno") AlumnoDto alumnoDto) {
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            ClaseDto claseDto = null;

            if (alumnoDto.getClaseDto().getId() != -1)
                claseDto = claseService.getClaseById(alumnoDto.getClaseDto().getId());

            if (claseDto != null) {
                alumnoClaseService.removeAlumnoCentro(alumnoDto);
                alumnoClaseService.addAlumnoLista(alumnoDto, claseDto);
            }

            if (alumnoDto.getFechaString() == null) {
                LocalDate date = alumnoService.getAlumnoById(alumnoDto.getId()).getFechaNac();
                alumnoDto.setFechaNac(date);
            } else {
                alumnoDto.setFechaNac(Utility.stringToDate(alumnoDto.getFechaString()));
            }

            alumnoDto = alumnoService.addAlumno(alumnoDto);

            str = "redirect:/alumno/alumno?id=" + alumnoDto.getId();
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/altaAlumno")
    public String altaAlumnoMethod(@RequestParam(value = "id") int id) {
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);

            if(null != alumnoDto){
                alumnoDto.setActivo("Activo");

                alumnoService.updateAlumno(alumnoDto);

                str = "redirect:/alumno/alumno?id=" + alumnoDto.getId();
            } else {
                str = "error/404";
            }
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/bajaAlumno")
    public String bajaAlumnoMethod(@RequestParam(value = "id") int id) {
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);

            if(null != alumnoDto){
                alumnoDto.setActivo("Inactivo");
                alumnoService.updateAlumno(alumnoDto);

                alumnoClaseService.removeAlumnoCentro(alumnoDto);

                str = "redirect:/alumno/alumno?id=" + alumnoDto.getId();
            } else {
                str = "error/404";
            }
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/addObservacion")
    public ModelAndView addObservacionMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav;
        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);

            if(null != alumnoDto){
                List<ObservacionDto> observacionDtoList = observacionService.listObservacionAlumno(alumnoDto);

                ObservacionDto observacionDto = new ObservacionDto();
                observacionDto.setAlumno(alumnoDto);
                observacionDto.setIdAlumno(id);

                mav = new ModelAndView("/alumno/crearObservacion");
                mav.addObject("observacion", observacionDto);
                mav.addObject("listaObservaciones", observacionDtoList);
                mav.addObject("usuario", empleadoService.getUserAuthenticated());
            } else {
                mav = new ModelAndView("error/404");
            }
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @PostMapping("/saveObservacion")
    public String saveAObservacionMethod(@Valid @ModelAttribute("observacion") ObservacionDto observacionDto) {
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            observacionDto.setFecha(LocalDate.now());
            observacionDto.setAlumno(alumnoService.getAlumnoById(observacionDto.getIdAlumno()));

            observacionService.addObservacion(observacionDto);

            str = "redirect:/alumno/alumno?id=" + observacionDto.getAlumno().getId();

        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/removeObservacion")
    public String removeObservacionMethod(@RequestParam(value = "id") int id) {
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            ObservacionDto observacionDto = observacionService.getObservacionById(id);

            if(null != observacionDto) {
                observacionService.removeObservacion(observacionDto);

                str = "redirect:/alumno/alumno?id=" + observacionDto.getAlumno().getId();
            } else {
                str = "error/404";
            }
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/removePago")
    public String removePagoMethod(@RequestParam(value = "id") int id) {
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            PagoDto pagoDto = pagoService.getPagoById(id);

            if(null != pagoDto) {
                pagoService.removePago(pagoDto);

                str = "redirect:/alumno/alumno?id=" + pagoDto.getAlumno().getId();
            } else {
                str = "error/404";
            }
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/faltasAlumno")
    public ModelAndView viewFaltasMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);

            if(null != alumnoDto) {
                List<FaltasDto> faltasDtoList = faltasService.listFaltasByAlumno(alumnoDto);

                mav = new ModelAndView("alumno/faltasAlumno");
                mav.addObject("faltas", faltasDtoList);
                mav.addObject("alumno", alumnoDto);
                mav.addObject("usuario", empleadoService.getUserAuthenticated());
            } else {
                mav = new ModelAndView("error/404");
            }
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

}
