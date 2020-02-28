package es.upo.tfg.manuelgandul.appkarate.controller;

import es.upo.tfg.manuelgandul.appkarate.model.centro.CentroDto;
import es.upo.tfg.manuelgandul.appkarate.model.centro.ResponsableDto;
import es.upo.tfg.manuelgandul.appkarate.service.centro.CentroService;
import es.upo.tfg.manuelgandul.appkarate.service.centro.ResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/centro")
public class CentroController {

    @Autowired
    @Qualifier("centroService")
    private CentroService centroService;

    @GetMapping("/")
    public String redirect(){
        return "redirect:/centro/centros";
    }

    @GetMapping("/centros")
    public String getCentrosMethod(Model model) {
        model.addAttribute("centros", centroService.listCentros());

        return "centro/centros";
    }

    @GetMapping("/centro")
    public ModelAndView viewCentroMethod(@RequestParam(value = "id") int id){
        ModelAndView mav = new ModelAndView("centro/centro");
        CentroDto centroDto = centroService.getCentroById(id);

        mav.addObject("centro", centroDto);

        return mav;
    }

    @GetMapping("/addCentro")
    public ModelAndView addCentroMethod() {
        ModelAndView mav = new ModelAndView("centro/crearCentro");

        CentroDto centroDto = new CentroDto();
        centroDto.setResponsable(new ResponsableDto());

        mav.addObject("centro", centroDto);

        return mav;
    }

    @PostMapping("/saveCentro")
    public String addCentroMethod(@Valid @ModelAttribute("centro") CentroDto centroDto) {
        centroDto = centroService.addCentro(centroDto);

        return "redirect:/centro/centro?id=" + centroDto.getId();
    }

    @GetMapping("/editCentro")
    public ModelAndView updateCentroMethod(@RequestParam(value = "id") int id){
        ModelAndView mav = new ModelAndView("centro/modificarCentro");

        mav.addObject("centro", centroService.getCentroById(id));

        return mav;
    }

    @PostMapping("/saveUpdatedCentro")
    public String saveUpdatedCentroMethod(@Valid @ModelAttribute("centro") CentroDto centroDto){
        centroDto = centroService.addCentro(centroDto);

        return "redirect:/centro/centro?id=" + centroDto.getId();
    }

    @GetMapping("/altaCentro")
    public String altaCentroMethod(@RequestParam(value = "id") int id) {
        CentroDto centroDto = centroService.getCentroById(id);

        centroDto.setActivo("Activo");
        centroService.updateCentro(centroDto);

        return "redirect:/centro/centro?id=" + centroDto.getId();
    }

    @GetMapping("/bajaCentro")
    public String bajaCentroMethod(@RequestParam(value = "id") int id) {
        CentroDto centroDto = centroService.getCentroById(id);

        centroDto.setActivo("Inactivo");
        centroService.updateCentro(centroDto);

        return "redirect:/centro/centro?id=" + centroDto.getId();
    }

}
