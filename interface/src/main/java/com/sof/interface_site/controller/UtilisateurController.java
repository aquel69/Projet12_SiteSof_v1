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
import java.util.ArrayList;
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

    private UtilisateurAuthentification utilisateurAuthentifier;
    private Utilisateur utilisateur;
    private NewsletterEmail newsletterEmail;
    private Conversation conversation;
    private Role role;
    private String error = null;
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
        utilisateur = new Utilisateur();

        if (utilisateurAuthentifier == null) {
            utilisateurAuthentifier = new UtilisateurAuthentification();
            role = new Role();

            role.setStatut("ROLE_USER");
            utilisateurAuthentifier.getRoles().add(0, role);
        }

        newsletterEmail = new NewsletterEmail();


        model.addAttribute("utilisateurAuthentifier", utilisateurAuthentifier);
        interfaceModelAccueil(model, utilisateur);

        System.out.println("Accueil");
        return "Index";
    }


    /**
     * permet de récupérer les donnéees saisies par l'utilisateur et de vérifier si l'authentification est valide
     * si elle l'est l'utilisateur est renvoyé sur la page d'accueil et le statut connecté apparait
     * dans la barre de menu ou l'admin est connecté sur sa page
     * @param model
     * @param utilisateurPost
     * @return la page correspondante au role
     */
    @RequestMapping(value = "/authentification",method = RequestMethod.POST)
    public String validationAuthentification(Model model, @ModelAttribute("utilisateur") UtilisateurAuth utilisateurPost){

        utilisateurAuthentifier = authentificationProxy.login(utilisateurPost);

        model.addAttribute("utilisateurAuthentifier", utilisateurAuthentifier);
        interfaceModelAccueil(model, utilisateur);

        if (utilisateurAuthentifier == null) {
            error = "L'email ou le mot de passe est incorrect";
            return "Index";
        } else if (utilisateurAuthentifier.getRoles().get(0).getIdRole() == 2){
            return "Index";
        } else {
            return "Newsletter";
        }
    }


    @RequestMapping(value = "/ajoutEmail",method = RequestMethod.POST)
    public String ajoutEmailNewsletter(Model model, @ModelAttribute("newsletterEmail") NewsletterEmail newsletterEmailPost){

        System.out.println(newsletterEmailPost.getEmail());

        newsletterEmailProxy.ajouterEmailNewsletter(newsletterEmailPost);

        interfaceModelAccueil(model, utilisateur);

        return "Index";
    }

    @RequestMapping(value = "/utilisateurEnvoiEmail",method = RequestMethod.POST)
    public String envoiMailDeLUtilisateur(Model model, @RequestParam String nom, @RequestParam String email
    , @RequestParam String message){

        newsletterEmailProxy.envoyerEmailUtilisateurAAdmin(nom, email, message);

        interfaceModelAccueil(model, utilisateur);

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

        messageInterface = "Votre compte a bien été créé";
        interfaceModelAccueil(model, utilisateur);
        messageInterface = null;

        return "Index";
    }


    @RequestMapping(value = "/conversationMembre", method = RequestMethod.GET)
    public String conversationMembre(Model model){
        conversation = new Conversation();
        interfaceModelConversation(model);

        System.out.println("ConversationMembre");
        return "ConversationMembre";
    }

    @RequestMapping(value = "/conversationMembre", method = RequestMethod.POST)
    public String conversationMembre(Model model, @ModelAttribute("conversation") @Valid Conversation conversation
            , BindingResult erreurConversation){
        conversation.setMessage(conversation.getMessage().replace("\n", "").replace("\r", ""));

        conversation.setMembre(utilisateurAuthentifier);
        conversation.setDateAjout(LocalDateTime.now());
        conversation.setInterlocuteur(utilisateurAuthentifier);

        conversationProxy.saveConversation(conversation);
        conversationProxy.conversationsSelonMembre(utilisateurAuthentifier.getIdUtilisateur());

        interfaceModelConversation(model);

        System.out.println("ConversationMembre");
        return "ConversationMembre";
    }




    private void interfaceModelAccueil(Model model, Utilisateur utilisateur) {
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
        List<Conversation> conversations = conversationProxy.conversationsSelonMembre(utilisateurAuthentifier.getIdUtilisateur());
        PhotoInterface photoInterface = interfaceDonneesProxy.getPhotoInterface(1);

        /*model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("adresse", adresse);*/
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

}
