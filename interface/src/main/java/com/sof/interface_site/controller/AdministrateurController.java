package com.sof.interface_site.controller;

import com.sof.interface_site.model.*;
import com.sof.interface_site.proxy.MicroserviceAuthentification;
import com.sof.interface_site.proxy.MicroserviceConcert;
import com.sof.interface_site.proxy.MicroserviceConversation;
import com.sof.interface_site.proxy.MicroserviceNewsletterEmail;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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

    /**
     * acc??der ?? la page newsletter si administrateur connect??
     * @param model model
     * @param id id
     * @return String
     */
    @RequestMapping(value = "/newsletter/{id}", method = RequestMethod.GET)
    public String redactionNewsletter(Model model, @PathVariable int id){
        String verificationUtilisateurConnecte = verificationUtilisateurConnecte(id);
        if (verificationUtilisateurConnecte.equals("Index")) {
            utilisateurController.accueil(model);
            return "Index";
        } else {
            listeMembresConversation = null;
            mail = new Mail();

            model.addAttribute("mail", mail);
            model.addAttribute("utilisateurAuthentifier", authentificationController.getUtilisateurAuthentifier());
        }
        return "Newsletter";
    }

    /**
     * envoyer la newsletter ?? tous les emails newsletter faisant partie de la base de donn??es
     * @param model model
     * @param mail mail
     * @param erreurMail erreurMail
     * @return String
     */
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

        String messageEnvoiMail = "Votre message a bien ??t?? envoy??";

        model.addAttribute("messageEnvoiMail", messageEnvoiMail);
        model.addAttribute("utilisateurAuthentifier", authentificationController.getUtilisateurAuthentifier());

        log.info("ajoutEmailNewsletter - ajout utilisateur de l'email : " + mail);

        return "Newsletter";
    }

    /**
     * acc??der ?? la page ajout et suppression concert si administrateur connect??
     * @param model model
     * @param id id
     * @return String
     */
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

    /**
     * ajouter un concert dans la base de donn??es en v??rifiant les donn??es saisies
     * @param model model
     * @param concert concert
     * @param erreurConcert erreurConcert
     * @param dateTime dateTime
     * @return String
     */
    @RequestMapping(value = "/ajoutConcertPost", method = RequestMethod.POST)
    public String ajouterUnConcert(Model model, @Valid @ModelAttribute("concert") ConcertDate concert
            , BindingResult erreurConcert, @RequestParam String dateTime){

        if (erreurConcert.hasErrors() || !verificationDate(dateTime)) {
            String messageErreurDate = "Le format de la date doit ??tre dd-mm-yyyy";

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

        log.info("ajouterUnConcert - ajout du concert : " + concert);

        return "AjoutConcert";
    }

    /**
     * supprimer un concert dans la base de donn??es en v??rifiant la selection
     * @param model model
     * @param idConcert idConcert
     * @return String
     */
    @RequestMapping(value = "/supprimerUnConcert", method = RequestMethod.POST)
    public String supprimerUnConcert(Model model, @RequestParam (defaultValue = "0") int idConcert){
        messagePasDeSelection = null;

        if (idConcert == 0) {
            modelConcert(model, concert);

            messagePasDeSelection = "vous n'avez rien s??lectionn??";
            model.addAttribute("messagePasDeSelection", messagePasDeSelection);

            return "AjoutConcert";
        }

        microserviceConcert.supprimerUnConcert(idConcert);
        listeDateConcert = microserviceConcert.findAllUtilisateur();

        modelConcert(model, concert);

        log.info("supprimerUnConcert - supprimer un concert : " + idConcert);

        return "AjoutConcert";
    }

    /**
     * acc??der ?? la page conversation si administrateur connect??
     * @param model model
     * @param id id
     * @return String
     */
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
                for (Conversation conversation : listeMembresConversation) {
                    if (conversation.getMembre().equals(3)) {
                        listeMembresConversation.remove(conversation);
                    }
                }
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

    /**
     * ajouter un message de conversation dans la base de donn??es et affichage de celui-ci
     * @param model model
     * @param conversation conversation
     * @return String
     * @throws IOException IOException
     */
    @RequestMapping(value = "/conversationAdministrateurPost", method = RequestMethod.POST)
    public String conversationAdministrateurPost(Model model, @ModelAttribute("conversation")
                ConversationBDD conversation) throws IOException {

        if (!verificationNbCaractereMessageConversation(conversation.getMessage())) {
            String messageErreurConversation = "le message est compris entre 2 et 500 caract??res";
            model.addAttribute("messageErreurConversation", messageErreurConversation);
            interfaceModelConversation(model);

            return "ConversationAdministrateur";
        }

        conversation.setMessage(conversation.getMessage()
                .replace("\n", "").replace("\r", ""));

        UtilisateurAuthentification utilisateurSof = microserviceAuthentification
                .findUtilisateurAuthentificationByUsername("Sof");

        conversation.setMembre(membreSelectionne.getIdUtilisateur());
        conversation.setDateAjout(LocalDateTime.now());
        conversation.setInterlocuteur(utilisateurSof.getIdUtilisateur());

        microserviceConversation.saveConversation(conversation);
        microserviceConversation.conversationsSelonMembre(membreSelectionne.getIdUtilisateur());
        newsletterEmailProxy.envoyerEmailConversation(membreSelectionne, "Vous avez un nouveau message de Sof", true);

        interfaceModelConversation(model);

        log.info("conversationAdministrateurPost - ajout de la conversation : " + conversation);

        return "ConversationAdministrateur";
    }

    /**
     * v??rification du membre s??lectionn?? et affichage de celui-ci
     * @param model model
     * @param usernameMembre usernameMembre
     * @return String
     */
    @RequestMapping(value = "/selectionDuMembre", method = RequestMethod.POST)
    public String selectionDuMembreConversation(Model model, @RequestParam(defaultValue = "0") String usernameMembre){
        messagePasDeSelection = null;

        if (usernameMembre.equals("0")) {
            //modelConcert(model, concert);

            messagePasDeSelection = "vous n'avez rien s??lectionn??";

            interfaceModelConversation(model);
            model.addAttribute("messagePasDeSelection", messagePasDeSelection);

            return "ConversationAdministrateur";
        }
        membreSelectionne = microserviceAuthentification.findUtilisateurAuthentificationByUsername(usernameMembre);
        microserviceConversation.conversationsSelonMembre(membreSelectionne.getIdUtilisateur());

        interfaceModelConversation(model);

        log.info("selectionDuMembreConversation - selection du membre pour la conversation : " + usernameMembre);

        return "ConversationAdministrateur";
    }

    /**
     * models pour la page concert
     * @param model model
     * @param concert concert
     */
    public void modelConcert(Model model, ConcertDate concert){
        model.addAttribute("listeDateConcert", listeDateConcert);
        model.addAttribute("concert", concert);
        model.addAttribute("dateTime", dateTime);
        model.addAttribute("utilisateurAuthentifier", authentificationController.getUtilisateurAuthentifier());
    }

    /**
     * models pour la page conversation
     * @param model model
     */
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

    /**
     * permet de v??rifier la syntaxe de la date
     * @param date date
     * @return boolean
     */
    public boolean verificationDate(final String date) {
        Matcher matcher = patternEmail.matcher(date);
        return matcher.matches();
    }

    /**
     * v??rification si un utilisateur est connect?? sinon renvoi la page index
     * @param id id
     * @return
     */
    private String verificationUtilisateurConnecte(int id) {
        UtilisateurAuthentification utilisateurVerification = microserviceAuthentification.findUtilisateurById(id);
        if( authentificationController.getUtilisateurAuthentifier() == null || !authentificationController
                .getUtilisateurAuthentifier().getUsername().equals(utilisateurVerification.getUsername())) {
             return "Index";
        }
        return "autre";
    }

    /**
     * v??rification du nombre de caract??res dans un message
     * @param message message
     * @return boolean
     */
    public boolean verificationNbCaractereMessageConversation(String message) {
        if (message.length() >= 2 && message.length() <= 500) {
            return true;
        } else {
            return false;
        }
    }
}
