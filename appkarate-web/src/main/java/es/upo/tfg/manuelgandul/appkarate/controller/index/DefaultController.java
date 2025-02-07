package es.upo.tfg.manuelgandul.appkarate.controller.index;

import es.upo.tfg.manuelgandul.appkarate.service.empleado.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class DefaultController {

    @Autowired
    @Qualifier("empleadoService")
    private EmpleadoService empleadoService;

    @GetMapping("/")
    public String indexMethod(Model model){
        String str;

        if(empleadoService.getUserAuthenticated().getTipoUsuario().equalsIgnoreCase("Empleado")){
            model.addAttribute("usuario", empleadoService.getUserAuthenticated());
            str = "default/indice";
        } else {
            str = "error/403";
        }

        return str;
    }

    @GetMapping("/login")
    public ModelAndView loginMethod(
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout){
        ModelAndView mav = new ModelAndView("default/login");

        mav.addObject("error", error);
        mav.addObject("logout", logout);

        return mav;
    }

    @GetMapping("/loginSuccess")
    public String loginCheckMethod(){
        return "redirect:/";
    }
}
