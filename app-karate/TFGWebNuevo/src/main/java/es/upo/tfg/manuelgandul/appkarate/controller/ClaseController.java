package es.upo.tfg.manuelgandul.appkarate.controller;

import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;
import es.upo.tfg.manuelgandul.appkarate.service.centro.CentroService;
import es.upo.tfg.manuelgandul.appkarate.service.clase.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clase")
public class ClaseController {

    @Autowired
    @Qualifier("claseService")
    private ClaseService claseService;

    @Autowired
    @Qualifier("centroService")
    private CentroService centroService;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/clase/clases";
    }

    @GetMapping("/clases")
    public String getClasesMethod(Model model) {
        model.addAttribute("clases", claseService.listClases());

        return "clase/clases";
    }

    @GetMapping("/clase")
    public ModelAndView viewClaseMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav = new ModelAndView("clase/clase");
        ClaseDto claseDto = claseService.getClaseById(id);

        mav.addObject("clase", claseDto);

        return mav;
    }

    @GetMapping("/addClase")
    public ModelAndView addClaseMethod() {
        ModelAndView mav = new ModelAndView("clase/crearClase");

        mav.addObject("clase", new ClaseDto());
        //mav.addObject();

        return mav;
    }
}
