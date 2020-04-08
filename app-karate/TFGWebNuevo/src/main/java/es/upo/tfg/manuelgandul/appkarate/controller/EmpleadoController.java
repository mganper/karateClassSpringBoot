package es.upo.tfg.manuelgandul.appkarate.controller;

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
        model.addAttribute("empleados", empleadoService.listEmpleados());
        model.addAttribute("usuario", empleadoService.getUserAuthenticated());

        return "empleado/empleados";
    }

    @GetMapping("/empleado")
    public ModelAndView viewEmpleadoMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav = new ModelAndView("empleado/empleado");

        mav.addObject("empleado", empleadoService.getEmpleadoById(id));
        mav.addObject("usuario", empleadoService.getUserAuthenticated());

        return mav;
    }

    @GetMapping("/addEmpleado")
    public ModelAndView addEmpleadoMethod() {
        ModelAndView mav = new ModelAndView("empleado/crearEmpleado");

        mav.addObject("empleado", new EmpleadoDto());
        mav.addObject("usuario", empleadoService.getUserAuthenticated());

        return mav;
    }

    @PostMapping("/saveEmpleado")
    public String addEmpleadoMethod(@Valid @ModelAttribute("empleado") EmpleadoDto empleadoDto) {
        empleadoDto.setFechaNacimiento(Utility.stringToDate(empleadoDto.getFechaString()));
        empleadoDto.setTipoUsuario("Empleado");
        empleadoDto.setContrasenya(Utility.passwordEncoder(empleadoDto.getContrasenya()));
        empleadoDto = empleadoService.addEmpleado(empleadoDto);

        return "redirect:/empleado/empleado?id=" + empleadoDto.getId();
    }

    @GetMapping("/editEmpleado")
    public ModelAndView updateEmpleadoMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav = new ModelAndView("empleado/modificarEmpleado");

        mav.addObject("empleado", empleadoService.getEmpleadoById(id));
        mav.addObject("usuario", empleadoService.getUserAuthenticated());

        return mav;
    }

    @PostMapping("/saveUpdatedEmpleado")
    public String saveUpdatedEmpleadoMethod(@Valid @ModelAttribute("empleado") EmpleadoDto empleadoDto) {
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

        return "redirect:/empleado/empleado?id=" + empleadoDto.getId();
    }

    @GetMapping("/altaEmpleado")
    public RedirectView altaEmpleadoMethod(@RequestParam(value = "id") int id) {
        EmpleadoDto empleadoDto = empleadoService.getEmpleadoById(id);

        empleadoDto.setActivo("Activo");
        empleadoService.updateEmpleado(empleadoDto);

        return new RedirectView("/empleado/empleado?id=" + empleadoDto.getId());
    }

    @GetMapping("/bajaEmpleado")
    public RedirectView bajaEmpleadoMethod(@RequestParam(value = "id") int id) {
        EmpleadoDto empleadoDto = empleadoService.getEmpleadoById(id);

        empleadoDto.setActivo("Inactivo");
        empleadoService.updateEmpleado(empleadoDto);

        return new RedirectView("/empleado/empleado?id=" + empleadoDto.getId());
    }

}
