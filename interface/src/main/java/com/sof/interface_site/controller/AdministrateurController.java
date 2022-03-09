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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
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
    private AuthentificationController authentificationController;

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
    private String messagePasDeSelection;


    @RequestMapping(value = "/newsletter/{id}", method = RequestMethod.GET)
    public String redactionNewsletter(Model model, @PathVariable int id){
        String verificationUtilisateurConnecte = verificationUtilisateurConnecte(id);
        if (verificationUtilisateurConnecte.equals("Index")) {
            utilisateurController.accueil(model);
            return "Index";
        } else {
            mail = new Mail();

            model.addAttribute("mail", mail);
            model.addAttribute("utilisateurAuthentifier", authentificationController.getUtilisateurAuthentifier());
        }
        return "Newsletter";
    }

    @RequestMapping(value = "/envoiNewsletter", method = RequestMethod.POST)
    public String envoiNewsletter(Model model, @ModelAttribute("mail") @Valid Mail mail, BindingResult erreurMail){
        List<NewsletterEmail> newsletterEmailList = microserviceNewsletterEmail.recupererTousLesEmailsNewsletter();

        if (erreurMail.hasErrors()) {
            model.addAttribute("mail", mail);
            model.addAttribute("utilisateurAuthentifier", authentificationController.getUtilisateurAuthentifier());

            return "Newsletter";
        }

        for (NewsletterEmail newsletterEmail : newsletterEmailList) {
            mail.setDestinataire(newsletterEmail.getEmail());
            newsletterEmailProxy.envoyerEmailNewsletter(mail);
        }

        model.addAttribute("mail", mail);

        String messageEnvoiMail = "Votre message a bien été envoyé";

        model.addAttribute("messageEnvoiMail", messageEnvoiMail);
        model.addAttribute("utilisateurAuthentifier", authentificationController.getUtilisateurAuthentifier());

        return "Newsletter";
    }

    @RequestMapping(value = "/ajoutConcert/{id}", method = RequestMethod.GET)
    public String ajoutConcert(Model model, @PathVariable int id){
        String verificationUtilisateurConnecte = verificationUtilisateurConnecte(id);
        if (verificationUtilisateurConnecte.equals("Index")) {
            utilisateurController.accueil(model);
            return "Index";
        } else {

            listeDateConcert = microserviceConcert.findAllUtilisateur();
            concert = new ConcertDate();

            modelConcert(model, concert);

        }

        return "AjoutConcert";
    }


    @RequestMapping(value = "/ajoutConcertPost", method = RequestMethod.POST)
    public String ajouterUnConcert(Model model, @Valid @ModelAttribute("concert") ConcertDate concert
            , BindingResult erreurConcert, @RequestParam String dateTime){

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
    public String supprimerUnConcert(Model model, @RequestParam (defaultValue = "0") int idConcert){
        messagePasDeSelection = null;

        if (idConcert == 0) {
            modelConcert(model, concert);

            messagePasDeSelection = "vous n'avez rien sélectionné";
            model.addAttribute("messagePasDeSelection", messagePasDeSelection);

            return "AjoutConcert";
        }
        microserviceConcert.supprimerUnConcert(idConcert);

        listeDateConcert = microserviceConcert.findAllUtilisateur();

        modelConcert(model, concert);

        return "AjoutConcert";
    }

    @RequestMapping(value = "/conversationAdministrateur/{id}", method = RequestMethod.GET)
    public String conversationAdministrateur(Model model, @PathVariable int id){
        String verificationUtilisateurConnecte = verificationUtilisateurConnecte(id);
        if (verificationUtilisateurConnecte.equals("Index")) {
            utilisateurController.accueil(model);
            return "Index";
        } else {
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
            model.addAttribute("utilisateurAuthentifier", authentificationController.getUtilisateurAuthentifier());
        }

        return "ConversationAdministrateur";
    }

    @RequestMapping(value = "/conversationAdministrateurPost", method = RequestMethod.POST)
    public String conversationAdministrateurPost(Model model, @ModelAttribute("conversation")
                Conversation conversation) throws IOException {

        if (verificationErreurMessageConversation(conversation.getMessage())) {
            String messageErreurConversation = "le message est compris entre 2 et 500 caractères";
            model.addAttribute("messageErreurConversation", messageErreurConversation);
            interfaceModelConversation(model);

            return "ConversationAdministrateur";
        }

        conversation.setMessage(conversation.getMessage()
                .replace("\n", "").replace("\r", ""));

        UtilisateurAuthentification utilisateurSof = microserviceAuthentification
                .findUtilisateurAuthentificationByUsername("Sof");

        conversation.setMembre(membreSelectionne);
        conversation.setDateAjout(LocalDateTime.now());
        conversation.setInterlocuteur(utilisateurSof);

        microserviceConversation.saveConversation(conversation);
        microserviceConversation.conversationsSelonMembre(membreSelectionne.getIdUtilisateur());

        newsletterEmailProxy.envoyerEmailConversation(authentificationController.getUtilisateurAuthentifier()
                , "Vous avez un nouveau message de Sof");

        interfaceModelConversation(model);

        return "ConversationAdministrateur";
    }

    @RequestMapping(value = "/selectionDuMembre", method = RequestMethod.POST)
    public String selectionDuMembreConversation(Model model, @RequestParam(defaultValue = "0") String usernameMembre){
        messagePasDeSelection = null;

        if (usernameMembre.equals("0")) {
            modelConcert(model, concert);

            messagePasDeSelection = "vous n'avez rien sélectionné";

            interfaceModelConversation(model);
            model.addAttribute("messagePasDeSelection", messagePasDeSelection);

            return "ConversationAdministrateur";
        }

        membreSelectionne = microserviceAuthentification.findUtilisateurAuthentificationByUsername(usernameMembre);
        microserviceConversation.conversationsSelonMembre(membreSelectionne.getIdUtilisateur());

        interfaceModelConversation(model);


        return "ConversationAdministrateur";
    }

    public void modelConcert(Model model, ConcertDate concert){
        model.addAttribute("listeDateConcert", listeDateConcert);
        model.addAttribute("concert", concert);
        model.addAttribute("dateTime", dateTime);
        model.addAttribute("utilisateurAuthentifier", authentificationController.getUtilisateurAuthentifier());
    }

    public boolean verificationDate(final String date) {
        Matcher matcher = patternEmail.matcher(date);
        return matcher.matches();
    }

    private void interfaceModelConversation(Model model) {

        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Berlin"));
        listeConversationSelonMembreSelectionne = microserviceConversation
                .conversationsSelonMembre(membreSelectionne.getIdUtilisateur());

        model.addAttribute("conversation", conversation);
        model.addAttribute("conversations", listeConversationSelonMembreSelectionne);
        model.addAttribute("localDateTimeMidnight",LocalDateTime.of(today, midnight));
        model.addAttribute("localDateTime",LocalDateTime.now());
        model.addAttribute("utilisateurAuthentifier", authentificationController.getUtilisateurAuthentifier());
        model.addAttribute("listeMembresConversation", listeMembresConversation);
        model.addAttribute("membreSelectionne", membreSelectionne);
    }

    private String verificationUtilisateurConnecte(int id) {
        UtilisateurAuthentification utilisateurVerification = microserviceAuthentification.findUtilisateurById(id);
        if( authentificationController.getUtilisateurAuthentifier() == null || !authentificationController.getUtilisateurAuthentifier().getUsername().equals(utilisateurVerification.getUsername())) {
             return "Index";
        }
        return "autre";
    }

    private boolean verificationErreurMessageConversation(String message) {
        if (message.length() >= 2 || message.length() <= 500) {
            return true;
        }
        return false;
    }
}
