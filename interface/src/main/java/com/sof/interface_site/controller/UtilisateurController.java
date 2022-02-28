package com.sof.interface_site.controller;

import com.sof.interface_site.model.*;
import com.sof.interface_site.proxy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UtilisateurController {

    private final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private final Pattern patternEmail = Pattern.compile(EMAIL_PATTERN);
    private final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    private final Pattern patternPassword = Pattern.compile(PASSWORD_PATTERN);

    @Autowired
    private MicroserviceAuthentification authentificationProxy;

    @Autowired
    private MicroserviceInterfaceDonnees interfaceDonneesProxy;

    @Autowired
    private MicroserviceConcert concertProxy;

    @Autowired
    private MicroserviceNewsletterEmail newsletterEmailProxy;

    @Autowired
    private MicroserviceConversation conversationProxy;

    @Autowired
    private AuthentificationController authentificationController;

    private UtilisateurAuthentification utilisateurAuthentifier;
    private Utilisateur utilisateur;
    private NewsletterEmail newsletterEmail;
    private Conversation conversation;
    private Role role;
    private String erreur = null;
    private boolean matchRegexEmail;
    private boolean matchRegexPassword;
    private String messageErreurMail = null;
    private String messageErreurMotDePasse = null;
    private String messageErreurConfirmationMail = null;
    private String messageErreurConfirmationMotDePasse = null;
    private String messageErreurUsernameDejaExistant = null;
    private String messageInterface = null;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String accueil(Model model){
        if (authentificationController.getUtilisateurAuthentifier() == null) {
            utilisateurAuthentifier = new UtilisateurAuthentification();
            role = new Role();
            utilisateur = new Utilisateur();
            newsletterEmail = new NewsletterEmail();

            role.setStatut("ROLE_USER");
            utilisateurAuthentifier.getRoles().add(0, role);
        } else {
            utilisateurAuthentifier = authentificationController.getUtilisateurAuthentifier();
        }

        interfaceModelAccueil(model);

        return "Index";
    }

    @RequestMapping(value = "/ajoutEmail",method = RequestMethod.POST)
    public String ajoutEmailNewsletter(Model model, @ModelAttribute("newsletterEmail") NewsletterEmail newsletterEmailPost){
        String messageEmailNewsletter = messageEmailNewsletter(newsletterEmailPost);

        if (messageEmailNewsletter.equals("Votre adresse a été ajoutée à la newsletter")) {
            newsletterEmailProxy.ajouterEmailNewsletter(newsletterEmailPost);
        }

        model.addAttribute("messageEmailNewsletter", messageEmailNewsletter);
        interfaceModelAccueil(model);

        return "Index";
    }

    @RequestMapping(value = "/ajoutEmailMembre",method = RequestMethod.POST)
    public String ajoutEmailNewsletterMembre(Model model, @RequestParam String email){
        NewsletterEmail newsletterEmail = new NewsletterEmail();
        newsletterEmail.setEmail(email);

        String messageEmailNewsletter = messageEmailNewsletter(newsletterEmail);

        if (messageEmailNewsletter.equals("Votre adresse a été ajoutée à la newsletter")) {
            newsletterEmailProxy.ajouterEmailNewsletter(newsletterEmail);
        }

        model.addAttribute("messageEmailNewsletter", messageEmailNewsletter);
        interfaceModelAccueil(model);

        return "Index";
    }

    @RequestMapping(value = "/utilisateurEnvoiEmail",method = RequestMethod.POST)
    public String envoiMailDeLUtilisateur(Model model, @RequestParam String nom, @RequestParam String email
    , @RequestParam String message){
        String messageEnvoiMail = "Votre message a bien été envoyé";

        newsletterEmailProxy.envoyerEmailUtilisateurAAdmin(nom, email, message);

        interfaceModelAccueil(model);

        model.addAttribute("messageEnvoiMail", messageEnvoiMail);

        return "Index";
    }

    @RequestMapping(value = "/creationCompte", method = RequestMethod.GET)
    public String creationCompte(Model model){
        Utilisateur utilisateur = new Utilisateur();
        Adresse adresse = new Adresse();

        interfaceModelCreationCompte(model, utilisateur, adresse);

        return "CreationCompte";
    }

    @RequestMapping(value = "/creationCompte", method = RequestMethod.POST)
    public String creationCompte(Model model, @ModelAttribute("utilisateur") @Valid Utilisateur utilisateur
            , BindingResult erreurUtilisateur, @ModelAttribute("adresse") @Valid Adresse adresse
            , BindingResult erreurAdresse, @RequestParam String confirmationEmail
            , @RequestParam String confirmationMotDePasse){
        matchRegexEmail = verificationEmail(utilisateur.getEmail());
        matchRegexPassword = verificationMotDePasse(utilisateur.getMotDePasse());
        messageErreurMail = null;
        messageErreurMotDePasse = null;
        messageErreurConfirmationMail = null;
        messageErreurConfirmationMotDePasse = null;
        messageErreurUsernameDejaExistant = null;
        List<Utilisateur> utilisateurs;

        utilisateurs = authentificationProxy.findAllUtilisateur();

        if (erreurUtilisateur.hasErrors() || erreurAdresse.hasErrors() || !matchRegexEmail  || !matchRegexPassword
        || !verificationCorrespondanceEmail(utilisateur.getEmail(), confirmationEmail)
        || !verificationCorrespondanceMotDePasse(utilisateur.getMotDePasse(), confirmationMotDePasse)
        || !verificationUsernameDejaExistant(utilisateurs, utilisateur)) {

            verificationErreursGlobale(utilisateur, confirmationEmail, confirmationMotDePasse, utilisateurs);
            interfaceModelCreationCompte(model, utilisateur, adresse, confirmationEmail, confirmationMotDePasse
                        , messageErreurMail, messageErreurMotDePasse, messageErreurConfirmationMail
                        , messageErreurConfirmationMotDePasse, messageErreurUsernameDejaExistant);

            return "CreationCompte";
        }

        Adresse adresseSave = authentificationProxy.addAdresse(adresse);
        utilisateur.setAdresseUtilisateur(adresseSave);
        utilisateur.setDateAjout(LocalDateTime.now());
        authentificationProxy.saveUtilisateur(utilisateur);
        authentificationProxy.saveRole(utilisateur);

        UtilisateurAuthentification utilisateurAuthentification = authentificationProxy
                .findUtilisateurAuthentificationByUsername(utilisateur.getUsername());
        newsletterEmailProxy.envoyerEmailBienvenue(utilisateurAuthentification);
        messageInterface = "Votre compte a bien été créé ! Un message vous a été envoyé sur votre boite mail";
        interfaceModelAccueil(model);
        messageInterface = null;

        return "Index";
    }

    @RequestMapping(value = "/modificationCompte/{id}", method = RequestMethod.GET)
    public String modificationCompte(Model model, @PathVariable int id){
        String verificationUtilisateurConnecte = verificationUtilisateurConnecte(id);
        if (verificationUtilisateurConnecte.equals("Index")) {
            accueil(model);
            return "Index";
        } else {
            Utilisateur utilisateur = authentificationProxy.findUtilisateurByUsername(authentificationController.getUtilisateurAuthentifier().getUsername());

            interfaceModelCreationCompte(model, utilisateur, utilisateur.getAdresseUtilisateur());
        }

        return "ModificationCompte";
    }

    @RequestMapping(value = "/modificationCompte", method = RequestMethod.POST)
    public String modificationCompte(Model model, @ModelAttribute("utilisateur") @Valid Utilisateur utilisateur
            , BindingResult erreurUtilisateur, @ModelAttribute("adresse") @Valid Adresse adresse
            , BindingResult erreurAdresse, @RequestParam String nouveauEmail
            , @RequestParam String nouveauMotDePasse){
        matchRegexEmail = verificationEmail(utilisateur.getEmail());
        matchRegexPassword = verificationMotDePasse(utilisateur.getMotDePasse());
        messageErreurMail = null;
        messageErreurMotDePasse = null;
        messageErreurConfirmationMail = null;
        messageErreurConfirmationMotDePasse = null;
        messageErreurUsernameDejaExistant = null;
        List<Utilisateur> utilisateurs;

        utilisateurs = authentificationProxy.findAllUtilisateur();

        if (erreurUtilisateur.hasErrors() || erreurAdresse.hasErrors() || !matchRegexEmail  || !matchRegexPassword
                || !verificationCorrespondanceEmail(utilisateur.getEmail(), nouveauEmail)
                || !verificationCorrespondanceMotDePasse(utilisateur.getMotDePasse(), nouveauMotDePasse)
                || !verificationUsernameDejaExistant(utilisateurs, utilisateur)) {

            verificationErreursGlobale(utilisateur, nouveauEmail, nouveauMotDePasse, utilisateurs);
            interfaceModelCreationCompte(model, utilisateur, adresse, nouveauEmail, nouveauMotDePasse
                    , messageErreurMail, messageErreurMotDePasse, messageErreurConfirmationMail
                    , messageErreurConfirmationMotDePasse, messageErreurUsernameDejaExistant);

            return "ModificationCompte";
        }

        Adresse adresseSave = authentificationProxy.addAdresse(adresse);
        utilisateur.setAdresseUtilisateur(adresseSave);
        utilisateur.setDateAjout(LocalDateTime.now());
        authentificationProxy.saveUtilisateur(utilisateur);
        authentificationProxy.saveRole(utilisateur);

        UtilisateurAuthentification utilisateurAuthentification = authentificationProxy
                .findUtilisateurAuthentificationByUsername(utilisateur.getUsername());
        newsletterEmailProxy.envoyerEmailBienvenue(utilisateurAuthentification);
        messageInterface = "Votre compte a bien été créé ! Un message vous a été envoyé sur votre boite mail";
        interfaceModelAccueil(model);
        messageInterface = null;

        return "ModificationCompte";
    }

    @RequestMapping(value = "/conversationMembre/{id}", method = RequestMethod.GET)
    public String conversationMembre(Model model, @PathVariable int id){
        String verificationUtilisateurConnecte = verificationUtilisateurConnecte(id);
        if (verificationUtilisateurConnecte.equals("Index")) {
            accueil(model);
            return "Index";
        } else {
            interfaceModelConversation(model);

            System.out.println("ConversationMembre");
            return "ConversationMembre";
        }
    }

    @RequestMapping(value = "/conversationMembre", method = RequestMethod.POST)
    public String conversationMembre(Model model, @ModelAttribute("conversation") @Valid Conversation conversation
            , BindingResult erreurConversation){
        conversation.setMessage(conversation.getMessage().replace("\n", "").replace("\r", ""));

        conversation.setMembre(authentificationController.getUtilisateurAuthentifier());
        conversation.setDateAjout(LocalDateTime.now());
        conversation.setInterlocuteur(authentificationController.getUtilisateurAuthentifier());

        conversationProxy.saveConversation(conversation);
        conversationProxy.conversationsSelonMembre(authentificationController.getUtilisateurAuthentifier().getIdUtilisateur());

        interfaceModelConversation(model);

        System.out.println("ConversationMembre");
        return "ConversationMembre";
    }

    public void interfaceModelAccueil(Model model) {
        //Accueil
        String urlVideoAccueil = interfaceDonneesProxy.getUrlVideoYoutube();
        //Biographie
        BiographieInterface biographieInterface = interfaceDonneesProxy.getBiographieInterface(1);
        //Concert
        ConcertInterface concertInterface = interfaceDonneesProxy.getConcertInterface(1);
        List<ConcertDate> listeDateConcert = concertProxy.findAllUtilisateur();
        //Album
        AlbumInterface albumInterface = interfaceDonneesProxy.getAlbumInterface(1);
        //Photo Interface
        PhotoInterface photoInterface = interfaceDonneesProxy.getPhotoInterface(1);

        model.addAttribute("urlVideo", urlVideoAccueil);
        model.addAttribute("concertInterface", concertInterface);
        model.addAttribute("biographieInterface", biographieInterface);
        model.addAttribute("albumInterface", albumInterface);
        model.addAttribute("photoInterface", photoInterface);
        model.addAttribute("listeDateConcert", listeDateConcert);
        model.addAttribute("newsletterEmail", newsletterEmail);
        model.addAttribute("utilisateur", utilisateurAuthentifier);
        model.addAttribute("messageCompteCree", messageInterface);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("utilisateurAuthentifier", utilisateurAuthentifier);
    }

    private void interfaceModelCreationCompte(Model model, Utilisateur utilisateur, Adresse adresse) {
        PhotoInterface photoInterface = interfaceDonneesProxy.getPhotoInterface(1);

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("adresse", adresse);
        model.addAttribute("photoInterface", photoInterface);
    }

    private void interfaceModelCreationCompte(Model model, Utilisateur utilisateur, Adresse adresse
            , String confirmationEmail, String confirmationMotDePasse
            , String messageErreurMail, String messageErreurMotDePasse, String messageErreurConfirmationMail
            , String messageErreurConfirmationMotDePasse, String messageErreurUsernameDejaExistant) {
        PhotoInterface photoInterface = interfaceDonneesProxy.getPhotoInterface(1);

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("adresse", adresse);
        model.addAttribute("photoInterface", photoInterface);
        model.addAttribute("confirmationEmail", confirmationEmail);
        model.addAttribute("confirmationMotDePasse", confirmationMotDePasse);
        model.addAttribute("messageErreurMail", messageErreurMail);
        model.addAttribute("messageErreurMotDePasse", messageErreurMotDePasse);
        model.addAttribute("messageErreurConfirmationMail", messageErreurConfirmationMail);
        model.addAttribute("messageErreurConfirmationMotDePasse", messageErreurConfirmationMotDePasse);
        model.addAttribute("messageErreurUsernameDejaExistant", messageErreurUsernameDejaExistant);

    }

    private void interfaceModelConversation(Model model) {
        conversation = new Conversation();
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Berlin"));
        List<Conversation> conversations = conversationProxy.conversationsSelonMembre(authentificationController
                .getUtilisateurAuthentifier().getIdUtilisateur());
        PhotoInterface photoInterface = interfaceDonneesProxy.getPhotoInterface(1);

        model.addAttribute("photoInterface", photoInterface);
        model.addAttribute("conversation", conversation);
        model.addAttribute("conversations", conversations);
        model.addAttribute("localDateTimeMidnight",LocalDateTime.of(today, midnight));
        model.addAttribute("localDateTime",LocalDateTime.now());

    }

    public void verificationErreursGlobale(Utilisateur utilisateur, String confirmationEmail, String confirmationMotDePasse
            , List<Utilisateur> utilisateurs){
        if (!matchRegexEmail) {
            messageErreurMail = "L\'adresse mail n'est pas valable";
        }
        if (!matchRegexPassword) {
            messageErreurMotDePasse = "Le mot de passe comprend de 8 à 20 caractères et contient" +
                    " au moins une majuscule, un chiffre et un caractère spécial ! @ # & ( )";
        }
        if (!verificationCorrespondanceEmail(utilisateur.getEmail(), confirmationEmail)) {
            messageErreurConfirmationMail = "Les adresses mails ne correspondent pas";
        }
        if (!verificationCorrespondanceMotDePasse(utilisateur.getMotDePasse(), confirmationMotDePasse)) {
            messageErreurConfirmationMotDePasse = "Les mots de passe ne correspondent pas";
        }
        if (!verificationUsernameDejaExistant(utilisateurs, utilisateur)) {
            messageErreurUsernameDejaExistant = "Le nom d'utilisateur est déjà existant";
        }
    }

    public String messageEmailNewsletter(NewsletterEmail newsletterEmail){
        List<NewsletterEmail> newsletterEmailList = newsletterEmailProxy.recupererTousLesEmailsNewsletter();

        if (!verificationEmail(newsletterEmail.getEmail())) {
            return "L\'adresse mail n'est pas valable";
        }
        if (!verificationEmailNewsletterDejaExistant(newsletterEmailList, newsletterEmail)) {
            return "Vous êtes déjà inscrit à la newsletter";
        }

        return "Votre adresse a été ajoutée à la newsletter";
    }

    public boolean verificationEmail(final String email) {
        Matcher matcher = patternEmail.matcher(email);
        return matcher.matches();
    }

    public boolean verificationMotDePasse(final String password) {
        Matcher matcher = patternPassword.matcher(password);
        return matcher.matches();
    }

    public boolean verificationCorrespondanceEmail(String email, String confirmationEmail) {
        if (email.equals(confirmationEmail)) {
            return true;
        }
        return false;
    }

    public boolean verificationCorrespondanceMotDePasse(String motDePasse, String confirmationMotDePasse) {
        if (motDePasse.equals(confirmationMotDePasse)) {
            return true;
        }
        return false;
    }

    public boolean verificationUsernameDejaExistant(List<Utilisateur> listeUtilisateurs, Utilisateur utilisateur) {
        for (Utilisateur utilisateurBoucle : listeUtilisateurs) {
            if (utilisateurBoucle.getUsername().equals(utilisateur.getUsername())) {
                return false;
            }
        }
        return true;
    }

    public boolean verificationEmailNewsletterDejaExistant(List<NewsletterEmail> listeEmailsNewletter, NewsletterEmail newsletterEmail) {
        for (NewsletterEmail newsletterEmailBoucle : listeEmailsNewletter) {
            if (newsletterEmailBoucle.getEmail().equals(newsletterEmail.getEmail())) {
                return false;
            }
        }
        return true;
    }

    private String verificationUtilisateurConnecte(int id) {
        UtilisateurAuthentification utilisateurVerification = authentificationProxy.findUtilisateurById(id);
        if( authentificationController.getUtilisateurAuthentifier() == null || !authentificationController.getUtilisateurAuthentifier().getUsername().equals(utilisateurVerification.getUsername())) {
            return "Index";
        }
        return "autre";
    }

}
