package es.upo.tfg.manuelgandul.appkarate.controller.empleado;

import es.upo.tfg.manuelgandul.appkarate.model.empleado.EmpleadoDto;
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
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    @Qualifier("empleadoService")
    private EmpleadoService empleadoService;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/empleado/empleados";
    }

    @GetMapping("/empleados")
    public String getEmpleadosMethod(Model model) {
        String str;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            model.addAttribute("empleados", empleadoService.listEmpleados());
            model.addAttribute("usuario", empleadoService.getUserAuthenticated());

            str = "empleado/empleados";
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/empleado")
    public ModelAndView viewEmpleadoMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            mav = new ModelAndView("empleado/empleado");

            mav.addObject("empleado", empleadoService.getEmpleadoById(id));
            mav.addObject("usuario", empleadoService.getUserAuthenticated());
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @GetMapping("/addEmpleado")
    public ModelAndView addEmpleadoMethod() {
        ModelAndView mav;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            mav = new ModelAndView("empleado/crearEmpleado");

            mav.addObject("empleado", new EmpleadoDto());
            mav.addObject("usuario", empleadoService.getUserAuthenticated());
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @PostMapping("/saveEmpleado")
    public String addEmpleadoMethod(@Valid @ModelAttribute("empleado") EmpleadoDto empleadoDto) {
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            empleadoDto.setFechaNacimiento(Utility.stringToDate(empleadoDto.getFechaString()));
            empleadoDto.setTipoUsuario("Empleado");
            empleadoDto.setContrasenya(Utility.passwordEncoder(empleadoDto.getContrasenya()));
            empleadoDto = empleadoService.addEmpleado(empleadoDto);

            str = "redirect:/empleado/empleado?id=" + empleadoDto.getId();
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/editEmpleado")
    public ModelAndView updateEmpleadoMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            mav = new ModelAndView("empleado/modificarEmpleado");

            mav.addObject("empleado", empleadoService.getEmpleadoById(id));
            mav.addObject("usuario", empleadoService.getUserAuthenticated());
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @PostMapping("/saveUpdatedEmpleado")
    public String saveUpdatedEmpleadoMethod(@Valid @ModelAttribute("empleado") EmpleadoDto empleadoDto) {
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
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

            str = "redirect:/empleado/empleado?id=" + empleadoDto.getId();
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/altaEmpleado")
    public String altaEmpleadoMethod(@RequestParam(value = "id") int id) {
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            EmpleadoDto empleadoDto = empleadoService.getEmpleadoById(id);

            empleadoDto.setActivo("Activo");
            empleadoService.updateEmpleado(empleadoDto);

            str = "redirect:/empleado/empleado?id=" + empleadoDto.getId();
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/bajaEmpleado")
    public String bajaEmpleadoMethod(@RequestParam(value = "id") int id) {
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            EmpleadoDto empleadoDto = empleadoService.getEmpleadoById(id);

            empleadoDto.setActivo("Inactivo");
            empleadoService.updateEmpleado(empleadoDto);

            str = "redirect:/empleado/empleado?id=" + empleadoDto.getId();
        } else {
            str = "error/403";
        }

        return str;
    }

}
