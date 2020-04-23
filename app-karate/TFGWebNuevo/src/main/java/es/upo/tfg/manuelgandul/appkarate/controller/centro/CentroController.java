package es.upo.tfg.manuelgandul.appkarate.controller.centro;

import es.upo.tfg.manuelgandul.appkarate.model.centro.CentroDto;
import es.upo.tfg.manuelgandul.appkarate.model.centro.ResponsableDto;
import es.upo.tfg.manuelgandul.appkarate.service.centro.CentroService;
import es.upo.tfg.manuelgandul.appkarate.service.centro.ResponsableService;
import es.upo.tfg.manuelgandul.appkarate.service.clase.ClaseService;
import es.upo.tfg.manuelgandul.appkarate.service.empleado.EmpleadoService;
import es.upo.tfg.manuelgandul.appkarate.service.relations.AlumnoClaseService;
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

    @Autowired
    @Qualifier("claseService")
    private ClaseService claseService;

    @Autowired
    @Qualifier("alumnoClaseService")
    private AlumnoClaseService alumnoClaseService;

    @Autowired
    @Qualifier("empleadoService")
    private EmpleadoService empleadoService;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/centro/centros";
    }

    @GetMapping("/centros")
    public String getCentrosMethod(Model model) {
        String str;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            List<CentroDto> centroDtoList = centroService.listCentros();

            centroDtoList.stream().forEach(centroDto -> {
                centroDto.setNumClases(claseService.getNumeroClasesByCentro(centroDto));
            });

            model.addAttribute("centros", centroDtoList);
            model.addAttribute("usuario", empleadoService.getUserAuthenticated());

            str = "centro/centros";
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/centro")
    public ModelAndView viewCentroMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav;

        if (empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            mav = new ModelAndView("centro/centro");
            CentroDto centroDto = centroService.getCentroById(id);

            mav.addObject("centro", centroDto);
            mav.addObject("usuario", empleadoService.getUserAuthenticated());
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @GetMapping("/addCentro")
    public ModelAndView addCentroMethod() {
        ModelAndView mav;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            mav = new ModelAndView("centro/crearCentro");

            CentroDto centroDto = new CentroDto();
            centroDto.setResponsable(new ResponsableDto());

            mav.addObject("centro", centroDto);
            mav.addObject("usuario", empleadoService.getUserAuthenticated());
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @PostMapping("/saveCentro")
    public String addCentroMethod(@Valid @ModelAttribute("centro") CentroDto centroDto) {
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            centroDto = centroService.addCentro(centroDto);
            str = "redirect:/centro/centro?id=" + centroDto.getId();
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/editCentro")
    public ModelAndView updateCentroMethod(@RequestParam(value = "id") int id) {
        ModelAndView mav;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            mav = new ModelAndView("centro/modificarCentro");

            mav.addObject("centro", centroService.getCentroById(id));
            mav.addObject("usuario", empleadoService.getUserAuthenticated());
        } else {
            mav = new ModelAndView("error/403");
        }

        return mav;
    }

    @PostMapping("/saveUpdatedCentro")
    public String saveUpdatedCentroMethod(@Valid @ModelAttribute("centro") CentroDto centroDto) {
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            centroDto = centroService.addCentro(centroDto);
            str = "redirect:/centro/centro?id=" + centroDto.getId();
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/altaCentro")
    public String altaCentroMethod(@RequestParam(value = "id") int id) {
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            CentroDto centroDto = centroService.getCentroById(id);

            centroDto.setActivo("Activo");
            centroService.updateCentro(centroDto);

            str = "redirect:/centro/centro?id=" + centroDto.getId();
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/bajaCentro")
    public String bajaCentroMethod(@RequestParam(value = "id") int id) {
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")) {
            CentroDto centroDto = centroService.getCentroById(id);

            centroDto.setActivo("Inactivo");
            centroService.updateCentro(centroDto);
            claseService.setBajaAllClasesByCentro(centroDto);
            alumnoClaseService.removeAllAlumnosByCentro(centroDto);

            str = "redirect:/centro/centro?id=" + centroDto.getId();
        } else {
            str = "error/403";
        }

        return str;
    }

}
