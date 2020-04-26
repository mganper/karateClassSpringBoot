package es.upo.tfg.manuelgandul.appkarate.controller.empleado;

import es.upo.tfg.manuelgandul.appkarate.model.empleado.EmpleadoDto;
import es.upo.tfg.manuelgandul.appkarate.service.common.CinturonService;
import es.upo.tfg.manuelgandul.appkarate.service.empleado.EmpleadoService;
import es.upo.tfg.manuelgandul.appkarate.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    @Qualifier("empleadoService")
    private EmpleadoService empleadoService;

    @Autowired
    @Qualifier("cinturonService")
    private CinturonService cinturonService;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/preofesor/profesores";
    }

    @GetMapping("/profesores")
    public String getProfesoresMethod(Model model) {
        String str;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            model.addAttribute("profesores", empleadoService.listProfesores());
            model.addAttribute("usuario", empleadoService.getUserAuthenticated());

            str = "profesor/profesores";
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/profesor")
    public ModelAndView viewProfesorMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            EmpleadoDto empleadoDto = empleadoService.getEmpleadoById(id);

            if (null != empleadoDto) {

                mav = new ModelAndView("profesor/profesor");

                mav.addObject("profesor", empleadoDto);
                mav.addObject("usuario", empleadoService.getUserAuthenticated());
            } else {
                mav = new ModelAndView("error/404");
            }
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @GetMapping("/addProfesor")
    public ModelAndView addProfesorMethod() {
        ModelAndView mav;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            mav = new ModelAndView("profesor/crearProfesor");

            mav.addObject("profesor", new EmpleadoDto());
            mav.addObject("cinturones", cinturonService.listCinturon());
            mav.addObject("usuario", empleadoService.getUserAuthenticated());
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @PostMapping("/saveProfesor")
    public String addProfesorMethod(@Valid @ModelAttribute("profesor") EmpleadoDto empleadoDto) {
        String str;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            empleadoDto.setFechaNacimiento(Utility.stringToDate(empleadoDto.getFechaString()));
            empleadoDto.setTipoUsuario("Profesor");
            empleadoDto.setContrasenya(Utility.passwordEncoder(empleadoDto.getContrasenya()));
            empleadoDto = empleadoService.addEmpleado(empleadoDto);

            str = "redirect:/profesor/profesor?id=" + empleadoDto.getId();
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/editProfesor")
    public ModelAndView updateProfesorMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            EmpleadoDto empleadoDto = empleadoService.getEmpleadoById(id);

            if (null != empleadoDto) {

                mav = new ModelAndView("profesor/modificarProfesor");

                mav.addObject("profesor", empleadoDto);
                mav.addObject("cinturones", cinturonService.listCinturon());
                mav.addObject("usuario", empleadoService.getUserAuthenticated());
            } else {
                mav = new ModelAndView("error/404");
            }
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @PostMapping("/saveUpdatedProfesor")
    public String saveUpdatedProfesorMethod(@Valid @ModelAttribute("profesor") EmpleadoDto empleadoDto) {
        String str;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            if (empleadoDto.getFechaString().equals("")) {
                empleadoDto.setFechaNacimiento(empleadoService.getEmpleadoById(empleadoDto.getId()).getFechaNacimiento());
            } else {
                empleadoDto.setFechaNacimiento(Utility.stringToDate(empleadoDto.getFechaString()));
            }

            if (empleadoDto.getContrasenya().equals("")) {
                empleadoDto.setContrasenya(empleadoService.getEmpleadoById(empleadoDto.getId()).getContrasenya());
            } else {
                empleadoDto.setContrasenya(Utility.passwordEncoder(empleadoDto.getContrasenya()));
            }

            empleadoDto = empleadoService.addEmpleado(empleadoDto);

            str = "redirect:/profesor/profesor?id=" + empleadoDto.getId();

        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/altaProfesor")
    public String altaProfesorMethod(@RequestParam(value = "id") int id) {
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            EmpleadoDto empleadoDto = empleadoService.getEmpleadoById(id);

            if(null != empleadoDto) {
                empleadoDto.setActivo("Activo");
                empleadoService.updateEmpleado(empleadoDto);

                str = "redirect:/empleado/empleado?id=" + empleadoDto.getId();
            } else {
                str = "error/404";
            }
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/bajaProfesor")
    public String bajaProfesorMethod(@RequestParam(value = "id") int id) {
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            EmpleadoDto empleadoDto = empleadoService.getEmpleadoById(id);

            if (null != empleadoDto) {
                empleadoDto.setActivo("Inactivo");
                empleadoService.updateEmpleado(empleadoDto);

                str = "redirect:/empleado/empleado?id=" + empleadoDto.getId();
            } else {
                str = "error/404";
            }
        } else {
            str = "error/403";
        }

        return str;
    }
}
