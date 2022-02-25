package com.sof.interface_site.controller;

import com.sof.interface_site.model.*;
import com.sof.interface_site.proxy.MicroserviceAuthentification;
import com.sof.interface_site.proxy.MicroserviceConcert;
import com.sof.interface_site.proxy.MicroserviceConversation;
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
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @Autowired
    private MicroserviceConversation microserviceConversation;

    @Autowired
    private UtilisateurController utilisateurController;

    private Mail mail;
    private ConcertDate concert;
    private String dateTime;
    private List<ConcertDate> listeDateConcert;
    private List<Conversation> listeMembresConversation;
    private UtilisateurAuthentification membreSelectionne;
    private Adresse adresseMembre;
    private List<Conversation> listeConversationSelonMembreSelectionne;
    private Conversation conversation;
    private UtilisateurAuthentification utilisateurSof;

    @RequestMapping(value = "/newsletter", method = RequestMethod.GET)
    public String redactionNewsletter(Model model){
        if(utilisateurSof == null) {
            utilisateurSof = utilisateurController.getUtilisateurAuthentifier();
        }

        if (utilisateurSof.getRoles().get(0).getStatut().equals("ROLE_ADMIN")) {
            mail = new Mail();

            model.addAttribute("mail", mail);
            System.out.println("Newsletter");
            return "Newsletter";
        } else {
            return utilisateurController.accueil(model);
        }
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
    public String conversationAdministrateur(Model model){
        if (listeMembresConversation == null) {
            listeMembresConversation = new ArrayList<>();
            listeConversationSelonMembreSelectionne = new ArrayList<>();
            listeMembresConversation = microserviceConversation.conversationSelonDateAjoutPourListeMembre();
            adresseMembre = new Adresse();
            membreSelectionne = new UtilisateurAuthentification(adresseMembre);
            conversation = new Conversation();
        }

        interfaceModelConversation(model);

        model.addAttribute("listeMembresConversation", listeMembresConversation);
        model.addAttribute("membreSelectionne", membreSelectionne);
        System.out.println("conversationAdministrateur");
        return "ConversationAdministrateur";
    }

    @RequestMapping(value = "/conversationAdministrateurPost", method = RequestMethod.POST)
    public String conversationAdministrateurPost(Model model, @ModelAttribute("conversation") @Valid Conversation conversation
            , BindingResult erreurConversation){
        conversation.setMessage(conversation.getMessage().replace("\n", "").replace("\r", ""));

        UtilisateurAuthentification utilisateurSof = microserviceAuthentification.findUtilisateurByUsername("Sof");

        conversation.setMembre(membreSelectionne);
        conversation.setDateAjout(LocalDateTime.now());
        conversation.setInterlocuteur(utilisateurSof);

        microserviceConversation.saveConversation(conversation);
        microserviceConversation.conversationsSelonMembre(membreSelectionne.getIdUtilisateur());

        interfaceModelConversation(model);
        model.addAttribute("listeMembresConversation", listeMembresConversation);
        model.addAttribute("membreSelectionne", membreSelectionne);

        System.out.println("ConversationMembre");
        return "ConversationAdministrateur";
    }

    @RequestMapping(value = "/selectionDuMembre", method = RequestMethod.POST)
    public String selectionDuMembreConversation(Model model, @RequestParam String usernameMembre){

        membreSelectionne = microserviceAuthentification.findUtilisateurByUsername(usernameMembre);
        microserviceConversation.conversationsSelonMembre(membreSelectionne.getIdUtilisateur());

        interfaceModelConversation(model);
        model.addAttribute("listeMembresConversation", listeMembresConversation);
        model.addAttribute("membreSelectionne", membreSelectionne);

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

    private void interfaceModelConversation(Model model) {

        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Berlin"));
        listeConversationSelonMembreSelectionne = microserviceConversation.conversationsSelonMembre(membreSelectionne.getIdUtilisateur());

        model.addAttribute("conversation", conversation);
        model.addAttribute("conversations", listeConversationSelonMembreSelectionne);
        model.addAttribute("localDateTimeMidnight",LocalDateTime.of(today, midnight));
        model.addAttribute("localDateTime",LocalDateTime.now());

    }

}
