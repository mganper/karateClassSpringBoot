package es.upo.tfg.manuelgandul.appkarate.controller;

import es.upo.tfg.manuelgandul.appkarate.model.Alumno;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    @GetMapping("/alumno")
    public String getAlumno(Model model) {
        Alumno al =  new Alumno("12345678A", "Manuel", "Gandul Pérez", true, new Date(22,01,1996), "651527748",
                "Calle La revoltosa", "Marrón");
        model.addAttribute("alumno", al);
        return "alumno/alumno";
    }

    @GetMapping("/alumnos")
    public String getAlumnos(Model model) {
        model.addAttribute("alumnos", alumnos());
        return "alumno/alumnos";
    }

    @GetMapping("/addAlumno")
    public String addAlumno() {
        return "alumno/crearAlumno";
    }

    /**
     * This method is for a temporal use, later I going to remove it.
     * @return A list of alumnos
     */
    private List<Alumno> alumnos(){
        List<Alumno> list = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            list.add(new Alumno("12345678A", "Manuel", "Gandul Pérez", true, new Date(22,01,1996), "651527748",
                    "Calle La revoltosa", "Marrón"));
        }

        return list;
    }
}
