package es.upo.tfg.manuelgandul.appkarate.controller;

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
        model.addAttribute("profesores", empleadoService.listProfesores());

        return "profesor/profesores";
    }

    @GetMapping("/profesor")
    public ModelAndView viewProfesorMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav = new ModelAndView("profesor/profesor");

        mav.addObject("profesor", empleadoService.getEmpleadoById(id));

        return mav;
    }

    @GetMapping("/addProfesor")
    public ModelAndView addProfesorMethod() {
        ModelAndView mav = new ModelAndView("profesor/crearProfesor");

        mav.addObject("profesor", new EmpleadoDto());
        mav.addObject("cinturones", cinturonService.listCinturon());

        return mav;
    }

    @PostMapping("/saveProfesor")
    public String addProfesorMethod(@Valid @ModelAttribute("profesor") EmpleadoDto empleadoDto){
        empleadoDto.setFechaNacimiento(Utility.stringToDate(empleadoDto.getFechaString()));

        empleadoDto = empleadoService.addEmpleado(empleadoDto);

        return "redirect:/profesor/profesor?id=" + empleadoDto.getId();
    }

    @GetMapping("/editProfesor")
    public ModelAndView updateProfesorMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav = new ModelAndView("profesor/modificarProfesor");

        mav.addObject("profesor", empleadoService.getEmpleadoById(id));
        mav.addObject("cinturones", cinturonService.listCinturon());

        return mav;
    }

    @PostMapping("/saveUpdatedProfesor")
    public String saveUpdatedProfesorMethod(@Valid @ModelAttribute("profesor") EmpleadoDto empleadoDto){
        if (empleadoDto.getFechaString().equals("")) {
            empleadoDto.setFechaNacimiento(empleadoService.getEmpleadoById(empleadoDto.getId()).getFechaNacimiento());
        } else {
            empleadoDto.setFechaNacimiento(Utility.stringToDate(empleadoDto.getFechaString()));
        }

        if(empleadoDto.getContrasenya().equals(""))
            empleadoDto.setContrasenya(empleadoService.getEmpleadoById(empleadoDto.getId()).getContrasenya());

        empleadoDto = empleadoService.addEmpleado(empleadoDto);

        return "redirect:/profesor/profesor?id=" + empleadoDto.getId();
    }

    @GetMapping("/altaProfesor")
    public RedirectView altaProfesorMethod(@RequestParam(value = "id") int id){
        EmpleadoDto empleadoDto = empleadoService.getEmpleadoById(id);

        empleadoDto.setActivo("Activo");
        empleadoService.updateEmpleado(empleadoDto);

        return new RedirectView("/profesor/profesor?id=" + empleadoDto.getId());
    }

    @GetMapping("/bajaProfesor")
    public RedirectView bajaProfesorMethod(@RequestParam(value = "id") int id){
        EmpleadoDto empleadoDto = empleadoService.getEmpleadoById(id);

        empleadoDto.setActivo("Inactivo");
        empleadoService.updateEmpleado(empleadoDto);

        return new RedirectView("/profesor/profesor?id=" + empleadoDto.getId());
    }
}
