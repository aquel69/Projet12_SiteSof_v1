package com.sof.interface_site.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UtilisateurController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String accueil(){
        System.out.println("Accueil");
        return "Index";
    }

    @RequestMapping(value = "/CreationCompte", method = RequestMethod.GET)
    public String inscription(){
        System.out.println("CreationCompte");
        return "CreationCompte";
    }

    @RequestMapping(value = "/ConversationMembre", method = RequestMethod.GET)
    public String conversationMembre(){
        System.out.println("ConversationMembre");
        return "ConversationMembre";
    }

}
