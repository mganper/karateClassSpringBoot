package es.upo.tfg.manuelgandul.appkarate.controller;

import es.upo.tfg.manuelgandul.appkarate.model.AlumnoModel;
import es.upo.tfg.manuelgandul.appkarate.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    @Qualifier("AlumnoService")
    private AlumnoService alumnoService;

    @GetMapping("/")
    public String redirect(){
        return "redirect:/alumno/alumnos";
    }

    @PostMapping("/alumno")
    public ModelAndView getAlumno(@Valid @ModelAttribute("alumno") AlumnoModel alumnoModel, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("alumno/alumno");
        mav.addObject(alumnoModel);

        alumnoService.addAlumno(alumnoModel);

        return mav;
    }

    @GetMapping("/alumnos")
    public String getAlumnos(Model model) {
        model.addAttribute("alumnos", alumnoService.listAllAlumnos());
        return "alumno/alumnos";
    }

    @GetMapping("/addAlumno")
    public String addAlumno(Model model) {
        model.addAttribute("alumno", new AlumnoModel());
        return "alumno/crearAlumno";
    }
}
