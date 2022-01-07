package com.sof.interface_site.controller;

import com.sof.interface_site.model.Utilisateur;
import com.sof.interface_site.model.UtilisateurAuthentification;
import com.sof.interface_site.proxie.MicroserviceAuthentification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UtilisateurController {

    @Autowired
    private MicroserviceAuthentification authentificationProxy;

    private UtilisateurAuthentification utilisateurAuthentifier;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String accueil(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateurGet){
        utilisateurAuthentifier = new UtilisateurAuthentification();
        System.out.println("Accueil");
        return "Index";
    }

    /**
     * permet de récupérer les donnéees saisies par l'utilisateur et de vérifier si l'authentification est valide
     * si elle l'est l'utilisateur est renvoyé sur la page d'accueil sinon il reste sur la page authentification
     * @param model
     * @param utilisateurPost
     * @return la page correspondante au role
     */
    @RequestMapping(value = "/",method = RequestMethod.POST )
    public String validationAuthentification(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateurPost){
        utilisateurAuthentifier = authentificationProxy.login(utilisateurPost.getMotDePasse(), utilisateurPost.getEmail());

        if (utilisateurAuthentifier == null) {
            return "Index";
        } else if (utilisateurAuthentifier.getRole() == 1){
            return "CreationCompte";
        } else {
            return "Newsletter";
        }
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
