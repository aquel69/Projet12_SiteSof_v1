package com.sof.interface_site.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UtilisateurController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String accueil(){
        System.out.println("coucou");
        return "Index";
    }

}
