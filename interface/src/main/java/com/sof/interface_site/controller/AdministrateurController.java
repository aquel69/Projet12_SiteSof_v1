package com.sof.interface_site.controller;

import com.sof.interface_site.model.ConcertDate;
import com.sof.interface_site.model.Mail;
import com.sof.interface_site.model.NewsletterEmail;
import com.sof.interface_site.proxy.MicroserviceAuthentification;
import com.sof.interface_site.proxy.MicroserviceConcert;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class AdministrateurController {

    private final String DATE_PATTERN = "^([0-2][0-9]||3[0-1])-(0[0-9]||1[0-2])-([0-9][0-9])?[0-9][0-9]$";

    private final Pattern patternEmail = Pattern.compile(DATE_PATTERN);

    @Autowired
    private MicroserviceNewsletterEmail newsletterEmailProxy;

    @Autowired
    private MicroserviceAuthentification microserviceAuthentification;

    @Autowired
    private MicroserviceNewsletterEmail microserviceNewsletterEmail;

    @Autowired
    private MicroserviceConcert microserviceConcert;

    private Mail mail;

    private ConcertDate concert;

    private String dateTime;

    private List<ConcertDate> listeDateConcert;

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

    @RequestMapping(value = "/ajoutConcert", method = RequestMethod.GET)
    public String ajoutConcert(Model model){
        listeDateConcert = microserviceConcert.findAllUtilisateur();
        concert = new ConcertDate();

        modelConcert(model, concert);

        return "AjoutConcert";
    }


    @RequestMapping(value = "/ajoutConcertPost", method = RequestMethod.POST)
    public String ajouterUnConcert(Model model, @Valid @ModelAttribute("concert") ConcertDate concert, BindingResult erreurConcert, @RequestParam String dateTime){

        if (erreurConcert.hasErrors() || !verificationDate(dateTime)) {
            String messageErreurDate = "Le format de la date doit être dd-mm-yyyy";

            model.addAttribute("messageErreurDate", messageErreurDate);
            modelConcert(model, concert);

            return "AjoutConcert";
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRANCE);

        LocalDateTime dateConcert = LocalDate.parse(dateTime, dtf).atStartOfDay();
        concert.setDate(dateConcert);
        microserviceConcert.ajouterUnConcert(concert);
        listeDateConcert = microserviceConcert.findAllUtilisateur();

        concert.setAdresse("");
        concert.setTarif("");
        concert.setNomLieu("");
        this.dateTime = null;

        modelConcert(model, concert);

        return "AjoutConcert";
    }

    @RequestMapping(value = "/supprimerUnConcert", method = RequestMethod.POST)
    public String supprimerUnConcert(Model model, @RequestParam int idConcert){

        microserviceConcert.supprimerUnConcert(idConcert);

        listeDateConcert = microserviceConcert.findAllUtilisateur();

        modelConcert(model, concert);

        return "AjoutConcert";
    }


    @RequestMapping(value = "/conversationAdministrateur", method = RequestMethod.GET)
    public String conversationAdministrateur(){
        System.out.println("conversationAdministrateur");
        return "ConversationAdministrateur";
    }

    public void modelConcert(Model model, ConcertDate concert){
        model.addAttribute("listeDateConcert", listeDateConcert);
        model.addAttribute("concert", concert);
        model.addAttribute("dateTime", dateTime);
    }

    public boolean verificationDate(final String date) {
        Matcher matcher = patternEmail.matcher(date);
        return matcher.matches();
    }

}
