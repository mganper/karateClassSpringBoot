package es.upo.tfg.manuelgandul.appkarate.controller.clase;

import es.upo.tfg.manuelgandul.appkarate.model.alumno.AlumnoDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.FaltasDto;
import es.upo.tfg.manuelgandul.appkarate.model.clase.ListaClaseDto;
import es.upo.tfg.manuelgandul.appkarate.model.relations.AlumnoClaseDto;
import es.upo.tfg.manuelgandul.appkarate.service.alumno.AlumnoService;
import es.upo.tfg.manuelgandul.appkarate.service.centro.CentroService;
import es.upo.tfg.manuelgandul.appkarate.service.clase.ClaseService;
import es.upo.tfg.manuelgandul.appkarate.service.clase.FaltasService;
import es.upo.tfg.manuelgandul.appkarate.service.empleado.EmpleadoService;
import es.upo.tfg.manuelgandul.appkarate.service.relations.AlumnoClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/clase")
public class ClaseController {

    @Autowired
    @Qualifier("claseService")
    private ClaseService claseService;

    @Autowired
    @Qualifier("centroService")
    private CentroService centroService;

    @Autowired
    @Qualifier("alumnoService")
    private AlumnoService alumnoService;

    @Autowired
    @Qualifier("empleadoService")
    private EmpleadoService empleadoService;

    @Autowired
    @Qualifier("alumnoClaseService")
    private AlumnoClaseService alumnoClaseService;

    @Autowired
    @Qualifier("faltasService")
    private FaltasService faltasService;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/clase/clases";
    }

    @GetMapping("/clases")
    public String getClasesMethod(Model model) {
        String str;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            model.addAttribute("clases", claseService.listClases());
            model.addAttribute("usuario", empleadoService.getUserAuthenticated());

            str = "clase/clases";
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/clase")
    public ModelAndView viewClaseMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            ClaseDto claseDto = claseService.getClaseById(id);

            if (null != claseDto) {
                int numAlumnos = alumnoClaseService.getNumeroAlumnosByClase(claseDto);
                claseDto.setNumAlumnos(numAlumnos);

                mav = new ModelAndView("clase/clase");
                mav.addObject("clase", claseDto);
                mav.addObject("usuario", empleadoService.getUserAuthenticated());
            } else {
                mav = new ModelAndView("error/404");
            }
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @GetMapping("/addClase")
    public ModelAndView addClaseMethod() {
        ModelAndView mav;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            mav = new ModelAndView("clase/crearClase");

            mav.addObject("clase", new ClaseDto());
            mav.addObject("centros", centroService.listCentros());
            mav.addObject("profesores", empleadoService.listProfesores());
            mav.addObject("usuario", empleadoService.getUserAuthenticated());
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @PostMapping("/saveClase")
    public String addClaseMethod(@Valid @ModelAttribute("clase") ClaseDto claseDto) {
        String str;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            if (claseDto.getProfesor().getId() == -1) {
                claseDto.setProfesor(null);
            }

            claseDto = claseService.addClase(claseDto);

            str = "redirect:/clase/clase?id=" + claseDto.getId();
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/editClase")
    public ModelAndView updateClaseMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            ClaseDto claseDto = claseService.getClaseById(id);

            if (null != claseDto) {
                mav = new ModelAndView("clase/modificarClase");

                mav.addObject("clase", claseDto);
                mav.addObject("centros", centroService.listCentros());
                mav.addObject("profesores", empleadoService.listProfesores());
                mav.addObject("usuario", empleadoService.getUserAuthenticated());
            } else {
                mav = new ModelAndView("error/404");
            }
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @PostMapping("/saveUpdatedClase")
    public String saveUpdatedClaseMethod(@Valid @ModelAttribute("clase") ClaseDto claseDto) {
        String str;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            if (claseDto.getProfesor().getId() == -1) {
                claseDto.setProfesor(null);
            }

            claseDto = claseService.addClase(claseDto);

            str = "redirect:/clase/clase?id=" + claseDto.getId();
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/altaClase")
    public String altaClaseMethod(@RequestParam(value = "id") int id) {
        String str;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            ClaseDto claseDto = claseService.getClaseById(id);

            if (null != claseDto) {

                if (claseDto.getCentro().getActivo().equals("Activo")) {
                    claseDto.setActivo("Activo");
                    claseService.updateClase(claseDto);
                }

                str = "redirect:/clase/clase?id=" + claseDto.getId();
            } else {
                str = "error/404";
            }
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/bajaClase")
    public String bajaClaseMethod(@RequestParam(value = "id") int id) {
        String str;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            ClaseDto claseDto = claseService.getClaseById(id);

            if (null != claseDto) {
                claseDto.setActivo("Inactivo");
                claseService.updateClase(claseDto);

                alumnoClaseService.removeAllAlumnosByClase(claseDto);

                str = "redirect:/clase/clase?id=" + claseDto.getId();
            } else {
                str = "error/404";
            }
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/listaClase")
    public ModelAndView listaClaseMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            ClaseDto claseDto = claseService.getClaseById(id);

            if (null != claseDto) {
                mav = new ModelAndView("clase/listaClase");

                List<AlumnoDto> alumnoDtoList = alumnoClaseService.listAlumnosByClase(claseDto);
                mav.addObject("alumnos", alumnoDtoList);
                mav.addObject("clase", claseDto);
                mav.addObject("usuario", empleadoService.getUserAuthenticated());
            } else {
                mav = new ModelAndView("error/404");
            }
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @GetMapping("/editListaClase")
    public ModelAndView updateListaClaseMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            ClaseDto claseDto = claseService.getClaseById(id);

            if (null != claseDto) {
                mav = new ModelAndView("clase/modificarListaClase");


                ListaClaseDto listaClaseDto = new ListaClaseDto(claseDto);
                List<AlumnoDto> alumnosDtoList = new ArrayList<>();
                List<AlumnoDto> allAlumnosDtoList = alumnoService.listAllAlumnosActivos();
                List<AlumnoClaseDto> alumnoClaseDtoList = alumnoClaseService.listAllAlumnoClase();

                List<AlumnoDto> alumnosConClase = new ArrayList<>();

                alumnoClaseDtoList.stream().forEach(alumnoClaseDto -> {
                    if (alumnoClaseDto.getClase().equals(claseDto)) {
                        listaClaseDto.getIdAlumnoList().add(alumnoClaseDto.getAlumno().getId());
                        allAlumnosDtoList.add(alumnoClaseDto.getAlumno());
                    }

                    allAlumnosDtoList.remove(alumnoClaseDto.getAlumno());
                });

                alumnosDtoList.addAll(allAlumnosDtoList);

                mav.addObject("alumnos", alumnosDtoList);
                mav.addObject("lista", listaClaseDto);
                mav.addObject("usuario", empleadoService.getUserAuthenticated());
            } else {
                mav = new ModelAndView("error/404");
            }
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @PostMapping("/saveUpdatedListaClase")
    public String saveUpdatedListaClaseMethod(@Valid @ModelAttribute("lista") ListaClaseDto listaClaseDto) {
        String str;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            listaClaseDto.setClaseDto(claseService.getClaseById(listaClaseDto.getClaseDto().getId()));
            alumnoClaseService.updateListaAlumnos(listaClaseDto);

            str = "redirect:/clase/listaClase?id=" + listaClaseDto.getClaseDto().getId();
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/faltasClase")
    public ModelAndView viewFaltasMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            ClaseDto claseDto = claseService.getClaseById(id);

            if (null != claseDto) {

                mav = new ModelAndView("clase/faltasClase");


                List<FaltasDto> faltasDtoList = faltasService.listFaltasByClase(claseDto);

                mav.addObject("faltas", faltasDtoList);
                mav.addObject("clase", claseDto);
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
