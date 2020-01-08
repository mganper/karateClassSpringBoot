package es.upo.tfg.manuelgandul.appkarate.controller;

import es.upo.tfg.manuelgandul.appkarate.model.Alumno;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public ModelAndView getAlumno(@Valid @ModelAttribute("alumno") Alumno al, BindingResult bindingResult) {
//        Alumno al =  new Alumno("12345678A", "Manuel", "Gandul Pérez", true, new Date(22,01,1996), "651527748",
//                "Calle La revoltosa", "Marrón");
//        model.addAttribute("alumno", al);
        ModelAndView mav = new ModelAndView("alumno/alumno");
        mav.addObject(al);

        return mav;
    }

    @GetMapping("/alumnos")
    public String getAlumnos(Model model) {
        model.addAttribute("alumnos", alumnos());
        return "alumno/alumnos";
    }

    @GetMapping("/addAlumno")
    public String addAlumno(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "alumno/crearAlumno";
    }

    /**
     * This method is for a temporal use, later I going to remove it.
     *
     * @return A list of alumnos
     */
    private List<Alumno> alumnos() {
        List<Alumno> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            list.add(new Alumno("12345678A", "Manuel", "Gandul Pérez", true, new Date(22, 01, 1996), "651527748",
                    "Calle La revoltosa", "Marrón"));
        }

        return list;
    }
}
