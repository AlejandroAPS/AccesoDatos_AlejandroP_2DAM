package es.ofertas.controladores;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class Controlador {
    @RequestMapping("/")
    ModelAndView peticionRaiz(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("cabecera", "Gestor de ofertas de trabajo");
        mv.setViewName("index");
        return mv;
    }


    @RequestMapping("/ofertas")
    ModelAndView peticionOfertas(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("cabecera", "Gestor de ofertas de trabajo");
        mv.setViewName("ofertas");
        return mv;
    }


    @RequestMapping("/madrid")
    ModelAndView peticionOfertasMadrid(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("cabecera", "Ofertas de Madrid");
        mv.setViewName("madird");
        return mv;
    }
}