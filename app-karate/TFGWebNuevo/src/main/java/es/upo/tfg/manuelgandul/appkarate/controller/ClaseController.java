package es.upo.tfg.manuelgandul.appkarate.controller;

import es.upo.tfg.manuelgandul.appkarate.model.clase.ClaseDto;
import es.upo.tfg.manuelgandul.appkarate.service.centro.CentroService;
import es.upo.tfg.manuelgandul.appkarate.service.clase.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

        mav.addObject("clase", claseService.getClaseById(id));

        return mav;
    }

    @GetMapping("/addClase")
    public ModelAndView addClaseMethod() {
        ModelAndView mav = new ModelAndView("clase/crearClase");

        mav.addObject("clase", new ClaseDto());
        mav.addObject("centros", centroService.listCentros());

        return mav;
    }

    @PostMapping("/saveClase")
    public String addCentroMethod(@Valid @ModelAttribute("clase") ClaseDto claseDto){
        claseDto = claseService.addClase(claseDto);

        return "redirect:/clase/clase?id=" + claseDto.getId();
    }

    @GetMapping("/editClase")
    public ModelAndView updateClaseMethod(@RequestParam(value = "id") int id){
        ModelAndView mav = new ModelAndView("clase/modificarClase");

        mav.addObject("clase", claseService.getClaseById(id));
        mav.addObject("centros", centroService.listCentros());

        return mav;
    }

    @PostMapping("/saveUpdatedClase")
    public String saveUpdatedCentroMethod(@Valid @ModelAttribute("clase") ClaseDto claseDto){
        claseDto = claseService.addClase(claseDto);

        return "redirect:/clase/clase?id=" + claseDto.getId();
    }

    @GetMapping("/altaClase")
    public String altaClaseMethod(@RequestParam(value = "id") int id) {
        ClaseDto claseDto = claseService.getClaseById(id);

        claseDto.setActivo("Activo");
        claseService.updateClase(claseDto);

        return "redirect:/clase/clase?id=" + claseDto.getId();
    }

    @GetMapping("/bajaClase")
    public String bajaClaseMethod(@RequestParam(value = "id") int id) {
        ClaseDto claseDto = claseService.getClaseById(id);

        claseDto.setActivo("Inactivo");
        claseService.updateClase(claseDto);

        return "redirect:/clase/clase?id=" + claseDto.getId();
    }

    // TODO: Hacer las cosas de lista aquí.


}
