package com.sof.interface_site.controller;

import com.sof.interface_site.model.NewsletterEmail;
import com.sof.interface_site.model.UtilisateurAuthentification;
import com.sof.interface_site.proxy.MicroserviceAuthentification;
import com.sof.interface_site.proxy.MicroserviceNewsletterEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdministrateurController {

    @Autowired
    private MicroserviceNewsletterEmail newsletterEmailProxy;

    @Autowired
    private MicroserviceAuthentification microserviceAuthentification;

    @Autowired
    private MicroserviceNewsletterEmail microserviceNewsletterEmail;

    @RequestMapping(value = "/newsletter", method = RequestMethod.GET)
    public String redactionNewsletter(Model model){



        System.out.println("Newsletter");
        return "Newsletter";
    }

    @RequestMapping(value = "/envoiNewsletter", method = RequestMethod.POST)
    public String envoiNewsletter(Model model, @RequestParam String objet, @RequestParam String message){
        List<NewsletterEmail> newsletterEmailList = microserviceNewsletterEmail.recupererTousLesEmailsNewsletter();

        for (NewsletterEmail newsletterEmail : newsletterEmailList) {
            newsletterEmailProxy.envoyerEmailNewsletter(objet, message, newsletterEmail.getEmail());
        }


        String messageEnvoiMail = "Votre message a bien été envoyé";

        model.addAttribute("messageEnvoiMail", messageEnvoiMail);

        System.out.println("Newsletter");
        return "Newsletter";
    }

    @RequestMapping(value = "/conversationAdministrateur", method = RequestMethod.GET)
    public String conversationAdministrateur(){
        System.out.println("conversationAdministrateur");
        return "ConversationAdministrateur";
    }


}
