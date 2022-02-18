package com.sof.interface_site.controller;

import com.sof.interface_site.model.Conversation;
import com.sof.interface_site.model.Mail;
import com.sof.interface_site.model.NewsletterEmail;
import com.sof.interface_site.model.UtilisateurAuthentification;
import com.sof.interface_site.proxy.MicroserviceAuthentification;
import com.sof.interface_site.proxy.MicroserviceNewsletterEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdministrateurController {

    @Autowired
    private MicroserviceNewsletterEmail newsletterEmailProxy;

    @Autowired
    private MicroserviceAuthentification microserviceAuthentification;

    @Autowired
    private MicroserviceNewsletterEmail microserviceNewsletterEmail;

    Mail mail;

    @RequestMapping(value = "/newsletter", method = RequestMethod.GET)
    public String redactionNewsletter(Model model){
        mail = new Mail();

        model.addAttribute("mail", mail);
        System.out.println("Newsletter");
        return "Newsletter";
    }

    @RequestMapping(value = "/envoiNewsletter", method = RequestMethod.POST)
    public String envoiNewsletter(Model model, @ModelAttribute("mail") @Valid Mail mail, BindingResult erreurMail){
        List<NewsletterEmail> newsletterEmailList = microserviceNewsletterEmail.recupererTousLesEmailsNewsletter();

        for (NewsletterEmail newsletterEmail : newsletterEmailList) {
            mail.setDestinataire(newsletterEmail.getEmail());
            newsletterEmailProxy.envoyerEmailNewsletter(mail);
        }

        model.addAttribute("mail", mail);

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
