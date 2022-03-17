package com.sof.interface_site.controller;

import com.sof.interface_site.model.*;
import com.sof.interface_site.proxy.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private static final Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UtilisateurAuthentification utilisateurAuthentifier;
    private Utilisateur utilisateur;
    private NewsletterEmail newsletterEmail;
    private Conversation conversation;
    private Role role;
    private String erreur = null;
    private boolean matchRegexEmail;
    private boolean matchRegexPassword;
    private boolean emailIdentique;
    private String messageErreurMail = null;
    private String messageErreurMotDePasse = null;
    private String messageErreurConfirmationMail = null;
    private String messageErreurConfirmationMotDePasse = null;
    private String messageErreurUsernameDejaExistant = null;
    private String messageInterface = null;
    private String messageErreurMailIdentique = null;
    private String messageErreurMotDePasseIdentique = null;
    private String messageErreurModificationMail = null;
    private String messageErreurModificationMotDePasse = null;
    private String messageErreurConfirmationMotDePasseVide = null;
    private String messageErreurNouveauMotDePasseVide = null;

    private BCryptPasswordEncoder bCryptPasswordMatche;

    /**
     * accéder à la page Accueil
     * @param model model
     * @return String
     */
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

        logger.info("accueil");

        interfaceModelAccueil(model);

        return "Index";
    }

    /**
     * ajout de l'email d'un utilisateur dans la base de données
     * @param model model
     * @param newsletterEmailPost newsletterEmailPost
     * @return String
     */
    @RequestMapping(value = "/ajoutEmail",method = RequestMethod.POST)
    public String ajoutEmailNewsletter(Model model, @ModelAttribute("newsletterEmail") NewsletterEmail newsletterEmailPost){
        String messageEmailNewsletter = messageErreurEmailNewsletter(newsletterEmailPost);

        if (messageEmailNewsletter.equals("Votre adresse a été ajoutée à la newsletter")) {
            newsletterEmailProxy.ajouterEmailNewsletter(newsletterEmailPost);
        }

        model.addAttribute("messageEmailNewsletter", messageEmailNewsletter);
        interfaceModelAccueil(model);

        return "Index";
    }

    /**
     * ajout de l'email d'un membre dans la base de données
     * @param model model
     * @param email email
     * @return String
     */
    @RequestMapping(value = "/ajoutEmailMembre",method = RequestMethod.POST)
    public String ajoutEmailNewsletterMembre(Model model, @RequestParam(defaultValue = "null") String email){
        NewsletterEmail newsletterEmail = new NewsletterEmail();
        newsletterEmail.setEmail(email);

        String messageEmailNewsletter = messageErreurEmailNewsletter(newsletterEmail);

        if (email.equals("null")) {
            messageEmailNewsletter = "Vous avez oublié de cocher la case avant de valider";
            model.addAttribute("messageEmailNewsletter", messageEmailNewsletter);
            utilisateurAuthentifier = authentificationController.getUtilisateurAuthentifier();
            interfaceModelAccueil(model);

            return "Index";
        }

        if (messageEmailNewsletter.equals("Votre adresse a été ajoutée à la newsletter")) {
            newsletterEmailProxy.ajouterEmailNewsletter(newsletterEmail);
        }

        model.addAttribute("messageEmailNewsletter", messageEmailNewsletter);
        interfaceModelAccueil(model);

        return "Index";
    }

    /**
     * envoi d'un mail d'un utilisateur à l'administrateur
     * @param model model
     * @param nom nom
     * @param email email
     * @param message message
     * @return String
     */
    @RequestMapping(value = "/utilisateurEnvoiEmail",method = RequestMethod.POST)
    public String envoiMailDeLUtilisateur(Model model, @RequestParam String nom, @RequestParam String email
    , @RequestParam String message){

        if (!verificationEmail(email) || !verificationErreurMessageConversation(message) || !verificationNomEnvoiEmail(nom)) {
            verificationErreurEnvoiMail(model, nom, email, message);
            return "Index";
        }

        newsletterEmailProxy.envoyerEmailUtilisateurAAdmin(nom, email, message);

        String messageEnvoiMail = "Votre message a bien été envoyé";

        interfaceModelAccueil(model);
        model.addAttribute("messageEnvoiMail", messageEnvoiMail);

        return "Index";
    }

    /**
     * accéder à la page création compte
     * @param model model
     * @return String
     */
    @RequestMapping(value = "/creationCompte", method = RequestMethod.GET)
    public String creationCompte(Model model){
        Utilisateur utilisateur = new Utilisateur();
        Adresse adresse = new Adresse();

        interfaceModelCreationCompte(model, utilisateur, adresse);

        return "CreationCompte";
    }

    /**
     * créer un compte membre dans la base de données et vérification des erreurs de saisie et de correspondance
     * @param model model
     * @param utilisateur utilisateur
     * @param erreurUtilisateur erreurUtilisateur
     * @param adresse adresse
     * @param erreurAdresse erreurAdresse
     * @param confirmationEmail confirmationEmail
     * @param confirmationMotDePasse confirmationMotDePasse
     * @return String
     */
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

            verificationErreursCreationCompte(utilisateur, confirmationEmail, confirmationMotDePasse, utilisateurs);
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

    /**
     * accéder à la page modification compte si membre connecté
     * @param model model
     * @param id id
     * @return String
     */
    @RequestMapping(value = "/modificationCompte/{id}", method = RequestMethod.GET)
    public String modificationCompte(Model model, @PathVariable int id){
        String verificationUtilisateurConnecte = verificationUtilisateurConnecte(id);
        if (verificationUtilisateurConnecte.equals("Index")) {
            accueil(model);
            return "Index";
        } else {
            Utilisateur utilisateur = authentificationProxy.findUtilisateurByUsername(authentificationController.getUtilisateurAuthentifier().getUsername());

            interfaceModelModificationCompte(model, utilisateur, utilisateur.getAdresseUtilisateur());
        }

        return "ModificationCompte";
    }

    /**
     * modifier un compte membre dans la base de données et vérification des erreurs de saisie et de correspondance
     * @param model model
     * @param utilisateur utilisateur
     * @param erreurUtilisateur erreurUtilisateur
     * @param adresse adresse
     * @param erreurAdresse erreurAdresse
     * @param nouveauEmail nouveauEmail
     * @param nouveauMotDePasse nouveauMotDePasse
     * @param usernameSupression usernameSupression
     * @param confirmationMotDePasse confirmationMotDePasse
     * @return String
     */
    @RequestMapping(value = "/modificationCompte", method = RequestMethod.POST)
    public String modificationComptePost(Model model, @ModelAttribute("utilisateur") @Valid Utilisateur utilisateur
            , BindingResult erreurUtilisateur, @ModelAttribute("adresse") @Valid Adresse adresse
            , BindingResult erreurAdresse, @RequestParam String nouveauEmail
            , @RequestParam String nouveauMotDePasse, @RequestParam String usernameSupression
            , @RequestParam String confirmationMotDePasse) {

        matchRegexPassword = true;
        matchRegexEmail = true;
        emailIdentique = false;
        messageErreurMotDePasseIdentique = null;
        messageErreurModificationMail = null;
        messageErreurModificationMotDePasse = null;
        messageErreurConfirmationMotDePasseVide = null;
        messageErreurNouveauMotDePasseVide = null;
        messageErreurMailIdentique = null;
        bCryptPasswordMatche = new BCryptPasswordEncoder();
        boolean correspondanceMotDePasse = true;
        boolean unChampsMotDePasseVide = false;

        //Récupération complet de l'utilisateur
        Utilisateur utilisateurComplet = authentificationProxy
                .findUtilisateurByUsername(authentificationController.getUtilisateurAuthentifier().getUsername());

        //Vérification et suppression du compte
        if (!usernameSupression.trim().isEmpty()) {
            if(verificationPourSupressionDuCompte(usernameSupression)) {
               supressionDuCompte();

                messageInterface = "Votre compte a été supprimé";
                utilisateurAuthentifier = new UtilisateurAuthentification();
                role = new Role();

                newsletterEmail = new NewsletterEmail();

                role.setStatut("ROLE_USER");
                utilisateurAuthentifier.getRoles().add(0, role);

                interfaceModelAccueil(model);
                messageInterface = null;

                return "Index";
            } else {
                String messageUsername = "Le username saisie ne correspond pas à celui existant";
                model.addAttribute("messageUsername", messageUsername);
                interfaceModelModificationCompte(model, utilisateur, adresse, messageErreurModificationMail
                        , messageErreurMotDePasseIdentique,  messageErreurModificationMotDePasse
                        , messageErreurConfirmationMotDePasseVide, messageErreurNouveauMotDePasseVide
                        , messageErreurMailIdentique);

                return "ModificationCompte";
            }
        }

        //Vérification et gestion des erreurs email et mot de passe
        if (!nouveauMotDePasse.trim().isEmpty() && !confirmationMotDePasse.trim().isEmpty()) {
            if (!nouveauMotDePasse.equals(confirmationMotDePasse)) {
                correspondanceMotDePasse = false;
            }
        } else if ((confirmationMotDePasse.trim().isEmpty() && !nouveauMotDePasse.trim().isEmpty())
                || (!confirmationMotDePasse.trim().isEmpty() && nouveauMotDePasse.trim().isEmpty())) {
            unChampsMotDePasseVide = true;
        }
        if (!nouveauMotDePasse.trim().isEmpty()) {
            matchRegexPassword = verificationMotDePasse(nouveauMotDePasse);
            utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(nouveauMotDePasse));
        } else {
            utilisateur.setMotDePasse(utilisateurComplet.getMotDePasse());
        }
        if (!nouveauEmail.trim().isEmpty()) {
            matchRegexEmail = verificationEmail(nouveauEmail);
            emailIdentique = verificationCorrespondanceEmail(utilisateurComplet.getEmail(),nouveauEmail);
            utilisateur.setEmail(nouveauEmail);
        } else {
            utilisateur.setEmail(utilisateurComplet.getEmail());
        }

        if (erreurUtilisateur.hasErrors() || erreurAdresse.hasErrors() || !matchRegexEmail  || !matchRegexPassword
                || !correspondanceMotDePasse || unChampsMotDePasseVide || emailIdentique) {
            verificationErreursModificationUtilisateur(nouveauMotDePasse,confirmationMotDePasse);
            interfaceModelModificationCompte(model, utilisateur, adresse, messageErreurModificationMail
                    , messageErreurMotDePasseIdentique,  messageErreurModificationMotDePasse
                    , messageErreurConfirmationMotDePasseVide, messageErreurNouveauMotDePasseVide
                    ,messageErreurMailIdentique);

            return "ModificationCompte";
        }

        //mise à jour de l'utilisateur et modification de l'adresse
        adresse.setIdAdresse(utilisateurComplet.getAdresseUtilisateur().getIdAdresse());
        utilisateur.setIdUtilisateur(utilisateurComplet.getIdUtilisateur());
        utilisateur.setAdresseUtilisateur(adresse);
        utilisateur.setRoles(utilisateurComplet.getRoles());
        utilisateur.setDateAjout(utilisateurComplet.getDateAjout());
        utilisateur.setUsername(utilisateurComplet.getUsername());

        authentificationProxy.modifierMembre(utilisateur);

        messageInterface = "Votre compte a bien été modifié";
        interfaceModelModificationCompte(model, utilisateur, utilisateur.getAdresseUtilisateur());
        model.addAttribute("adresseMail", utilisateur.getEmail());
        messageInterface = null;

        return "ModificationCompte";
    }

    /**
     * accéder à la page conversation si le membre est connecté
     * @param model model
     * @param id id
     * @return String
     */
    @RequestMapping(value = "/conversationMembre/{id}", method = RequestMethod.GET)
    public String conversationMembre(Model model, @PathVariable int id){
        String verificationUtilisateurConnecte = verificationUtilisateurConnecte(id);
        if (verificationUtilisateurConnecte.equals("Index")) {
            accueil(model);

            return "Index";
        } else {
            interfaceModelConversation(model);

            return "ConversationMembre";
        }
    }

    /**
     * ajouter une conversation membre dans la base de données et et vérification des erreurs de saisie
     * @param model model
     * @param conversation conversation
     * @return String
     * @throws IOException IOException
     */
    @RequestMapping(value = "/conversationMembre", method = RequestMethod.POST)
    public String conversationMembre(Model model, @ModelAttribute("conversation") ConversationBDD conversation) throws IOException {
        if (!verificationErreurMessageConversation(conversation.getMessage())) {
            String messageErreurConversation = "le message est compris entre 2 et 500 caractères";
            model.addAttribute("messageErreurConversation", messageErreurConversation);
            interfaceModelConversation(model);

            return "ConversationMembre";
        }

        conversation.setMessage(conversation.getMessage().replace("\n", "").replace("\r", ""));
        conversation.setMembre(authentificationController.getUtilisateurAuthentifier().getIdUtilisateur());
        conversation.setDateAjout(LocalDateTime.now());
        conversation.setInterlocuteur(authentificationController.getUtilisateurAuthentifier().getIdUtilisateur());

        conversationProxy.saveConversation(conversation);
        conversationProxy.conversationsSelonMembre(authentificationController.getUtilisateurAuthentifier().getIdUtilisateur());

        newsletterEmailProxy.envoyerEmailConversation(authentificationController.getUtilisateurAuthentifier()
                , "Vous avez un nouveau message de "
                        + authentificationController.getUtilisateurAuthentifier().getUsername(), false);

        interfaceModelConversation(model);

        return "ConversationMembre";
    }

    /**
     * accéder à la page désinscription newsletter
     * @param model model
     * @return String
     */
    @RequestMapping(value = "/desinscriptionNewsletter", method = RequestMethod.GET)
    public String desinscriptionNewsletter(Model model){
        interfaceDesinscritpionNewsletter(model);

        return "DesinscriptionNewsletter";
    }

    /**
     * désinscrire un utilisateur de la newsletter et vérification des erreurs de correspondance
     * @param model model
     * @param email email
     * @return String
     */
    @RequestMapping(value = "/desinscriptionNewsletter",method = RequestMethod.POST)
    public String desinscriptionNewsletter(Model model, @RequestParam String email){
        if (!email.trim().isEmpty()) {
            List<NewsletterEmail> newsletterEmailList = newsletterEmailProxy.recupererTousLesEmailsNewsletter();
            for (NewsletterEmail newsletterEmail : newsletterEmailList) {
                if (newsletterEmail.getEmail().equals(email)) {
                    newsletterEmailProxy.supprimerEmailNewsletter(email);
                    messageInterface = "Votre Email a bien été supprimé de la liste des newsLetters";

                    interfaceDesinscritpionNewsletter(model);
                    messageInterface = null;

                    return "DesinscriptionNewsletter";
                }
            }
        }
        messageErreurMail = "Votre Email n'est pas dans la liste des newsLetters";
        interfaceDesinscritpionNewsletter(model);
        messageErreurMail = null;

        return "DesinscriptionNewsletter";
    }

    /**
     * models pour la page accueil
     * @param model model
     */
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

    /**
     * models pour la page création compte
     * @param model model
     * @param utilisateur  utilisateur
     * @param adresse adresse
     */
    private void interfaceModelCreationCompte(Model model, Utilisateur utilisateur, Adresse adresse) {
        PhotoInterface photoInterface = interfaceDonneesProxy.getPhotoInterface(1);

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("adresse", adresse);
        model.addAttribute("photoInterface", photoInterface);
    }

    /**
     * models pour la page création compte
     * @param model model
     * @param utilisateur utilisateur
     * @param adresse adresse
     * @param confirmationEmail confirmationEmail
     * @param confirmationMotDePasse confirmationMotDePasse
     * @param messageErreurMail messageErreurMail
     * @param messageErreurMotDePasse messageErreurMotDePasse
     * @param messageErreurConfirmationMail messageErreurConfirmationMail
     * @param messageErreurConfirmationMotDePasse messageErreurConfirmationMotDePasse
     * @param messageErreurUsernameDejaExistant messageErreurUsernameDejaExistant
     */
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

    /**
     * models pour la page modification compte
     * @param model model
     * @param utilisateur utilisateur
     * @param adresse adresse
     */
    private void interfaceModelModificationCompte(Model model, Utilisateur utilisateur, Adresse adresse) {
        PhotoInterface photoInterface = interfaceDonneesProxy.getPhotoInterface(1);

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("adresse", adresse);
        model.addAttribute("photoInterface", photoInterface);
        model.addAttribute("adresseMail", authentificationController.getUtilisateurAuthentifier().getEmail());
        model.addAttribute("messageInterface", messageInterface);
    }

    /**
     * models pour la page modification compte
     * @param model model
     * @param utilisateur utilisateur
     * @param adresse adresse
     * @param messageErreurModificationMail messageErreurModificationMail
     * @param messageErreurMotDePasseIdentique messageErreurMotDePasseIdentique
     * @param messageErreurModificationMotDePasse messageErreurModificationMotDePasse
     * @param messageErreurConfirmationMotDePasseVide messageErreurConfirmationMotDePasseVide
     * @param messageErreurNouveauMotDePasseVide messageErreurNouveauMotDePasseVide
     * @param messageErreurMailIdentique messageErreurMailIdentique
     */
    private void interfaceModelModificationCompte(Model model, Utilisateur utilisateur, Adresse adresse
            , String messageErreurModificationMail, String messageErreurMotDePasseIdentique, String messageErreurModificationMotDePasse
            , String messageErreurConfirmationMotDePasseVide, String messageErreurNouveauMotDePasseVide
            , String messageErreurMailIdentique) {

        PhotoInterface photoInterface = interfaceDonneesProxy.getPhotoInterface(1);

        model.addAttribute("adresseMail", authentificationController.getUtilisateurAuthentifier().getEmail());
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("adresse", adresse);
        model.addAttribute("photoInterface", photoInterface);
        model.addAttribute("messageErreurMotDePasseIdentique", messageErreurMotDePasseIdentique);
        model.addAttribute("messageErreurModificationMail", messageErreurModificationMail);
        model.addAttribute("messageErreurModificationMotDePasse", messageErreurModificationMotDePasse);
        model.addAttribute("messageErreurConfirmationMotDePasseVide", messageErreurConfirmationMotDePasseVide);
        model.addAttribute("messageErreurNouveauMotDePasseVide", messageErreurNouveauMotDePasseVide);
        model.addAttribute("messageErreurMailIdentique", messageErreurMailIdentique);
    }

    /**
     * models pour la page conversation membre
     * @param model model
     */
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

    /**
     * models pour la page désinscription de la newsletter
     * @param model model
     */
    public void interfaceDesinscritpionNewsletter(Model model) {
        PhotoInterface photoInterface = interfaceDonneesProxy.getPhotoInterface(1);

        model.addAttribute("photoInterface", photoInterface);
        model.addAttribute("messageEmailSupprime", messageInterface);
        model.addAttribute("messageErreurEmail", messageErreurMail);
    }

    /**
     * gestion des erreurs lors de l'envoi d'un mail à l'administrateur
     * @param model model
     * @param nom nom
     * @param email email
     * @param message message
     */
    public void verificationErreurEnvoiMail(Model model, String nom, String email, String message) {
            if(!verificationEmail(email)) {
                String messageErreurEmail = "L\'adresse mail n'est pas valable";
                model.addAttribute("messageErreurEmail", messageErreurEmail);
            }
            if(!verificationErreurMessageConversation(message)) {
                String messageErreurMessage = "Le message est compris entre 2 et 500 caractères";
                model.addAttribute("messageErreurMessage", messageErreurMessage);
            }
            if(!verificationNomEnvoiEmail(nom)) {
                String messageErreurNom = "Le nom est compris entre 2 et 20 caractères";
                model.addAttribute("messageErreurNom", messageErreurNom);
            }
            interfaceModelAccueil(model);
    }

    /**
     * gestion des erreurs lors de la modification d'un compte
     * @param nouveauMotDePasse nouveauMotDePasse
     * @param confirmationMotDePasse confirmationMotDePasse
     */
    public void verificationErreursModificationUtilisateur(String nouveauMotDePasse, String confirmationMotDePasse){
        if (!matchRegexEmail) {
            messageErreurModificationMail = "L\'adresse mail n'est pas valable";
        }

        if (emailIdentique) {
            messageErreurMailIdentique = "L\'adresse mail est identique à la votre";
        }

        if (!matchRegexPassword) {
            messageErreurModificationMotDePasse = "Le mot de passe comprend de 8 à 20 caractères et contient" +
                    " au moins une majuscule, un chiffre et un caractère spécial ! @ # & ( )";
        }

        if (nouveauMotDePasse.trim().isEmpty() && !confirmationMotDePasse.trim().isEmpty()) {
            messageErreurNouveauMotDePasseVide = "Vous devez saisir votre nouveau mot de passe";
        }

        if (!nouveauMotDePasse.trim().isEmpty() && confirmationMotDePasse.trim().isEmpty()) {
            messageErreurConfirmationMotDePasseVide = "Vous devez confirmer votre nouveau mot de passe";
        }

        if (!nouveauMotDePasse.trim().isEmpty() && !confirmationMotDePasse.trim().isEmpty()) {
            if(!nouveauMotDePasse.equals(confirmationMotDePasse)){
                messageErreurMotDePasseIdentique = "Le mot de passe et la confirmation ne correspondent pas";
            }
        }
    }

    /**
     * gestion des erreurs lors de la création d'un compte
     * @param utilisateur utilisateur
     * @param confirmationEmail confirmationEmail
     * @param confirmationMotDePasse confirmationMotDePasse
     * @param utilisateurs utilisateurs
     */
    public void verificationErreursCreationCompte(Utilisateur utilisateur, String confirmationEmail, String confirmationMotDePasse
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

    /**
     * gestion des erreurs lors de l'inscription à la newsletter
     * @param newsletterEmail newsletterEmail
     * @return String
     */
    public String messageErreurEmailNewsletter(NewsletterEmail newsletterEmail){
        List<NewsletterEmail> newsletterEmailList = newsletterEmailProxy.recupererTousLesEmailsNewsletter();
        if (!verificationEmail(newsletterEmail.getEmail())) {
            return "L\'adresse mail n'est pas valable";
        }
        if (!verificationEmailNewsletterDejaExistant(newsletterEmailList, newsletterEmail)) {
            return "Vous êtes déjà inscrit à la newsletter";
        }

        return "Votre adresse a été ajoutée à la newsletter";
    }

    /**
     * vérification de la syntaxe de l'email
     * @param email email
     * @return boolean
     */
    public boolean verificationEmail(final String email) {
        Matcher matcher = patternEmail.matcher(email);
        return matcher.matches();
    }

    /**
     * vérification de la syntaxe du mot de passe
     * @param password password
     * @return boolean
     */
    public boolean verificationMotDePasse(final String password) {
        Matcher matcher = patternPassword.matcher(password);
        return matcher.matches();
    }

    /**
     * vérification de la correspondance des emails
     * @param email email
     * @param confirmationEmail confirmationEmail
     * @return boolean
     */
    public boolean verificationCorrespondanceEmail(String email, String confirmationEmail) {
        if (email.equals(confirmationEmail)) {
            return true;
        }
        return false;
    }

    /**
     * vérification de la correspondance des mots de passe
     * @param motDePasse motDePasse
     * @param confirmationMotDePasse confirmationMotDePasse
     * @return boolean
     */
    public boolean verificationCorrespondanceMotDePasse(String motDePasse, String confirmationMotDePasse) {
        if (motDePasse.equals(confirmationMotDePasse)) {
            return true;
        }
        return false;
    }

    /**
     * vérification d'un username déjà existant
     * @param listeUtilisateurs listeUtilisateurs
     * @param utilisateur utilisateur
     * @return boolean
     */
    public boolean verificationUsernameDejaExistant(List<Utilisateur> listeUtilisateurs, Utilisateur utilisateur) {
        for (Utilisateur utilisateurBoucle : listeUtilisateurs) {
            if (utilisateurBoucle.getUsername().equals(utilisateur.getUsername())) {
                return false;
            }
        }
        return true;
    }

    /**
     * vérification d'un email newsletter déjà existant
     * @param listeEmailsNewletter listeEmailsNewletter
     * @param newsletterEmail newsletterEmail
     * @return boolean
     */
    public boolean verificationEmailNewsletterDejaExistant(List<NewsletterEmail> listeEmailsNewletter
            , NewsletterEmail newsletterEmail) {
        for (NewsletterEmail newsletterEmailBoucle : listeEmailsNewletter) {
            if (newsletterEmailBoucle.getEmail().equals(newsletterEmail.getEmail())) {
                return false;
            }
        }
        return true;
    }

    /**
     * vérification si un utilisateur est connecté sinon renvoi à la page index
     * @param id id
     * @return String
     */
    private String verificationUtilisateurConnecte(int id) {
        UtilisateurAuthentification utilisateurVerification = authentificationProxy.findUtilisateurById(id);
        if( authentificationController.getUtilisateurAuthentifier() == null || !authentificationController
                .getUtilisateurAuthentifier().getUsername().equals(utilisateurVerification.getUsername())) {
            return "Index";
        }
        return "autre";
    }

    /**
     * vérification si un utilisateur est connecté pour la suppression du compte
     * @param usernameSupression usernameSupression
     * @return boolean
     */
    private boolean verificationPourSupressionDuCompte(String usernameSupression) {
        if (usernameSupression.equals(authentificationController.getUtilisateurAuthentifier().getUsername())) {
               return true;
        }

        return false;
    }

    /**
     * vérification du nombre de caractères dans un message
     * @param message message
     * @return boolean
     */
    private boolean verificationErreurMessageConversation(String message) {
        if (message.length() >= 2 && message.length() <= 500) {
            return true;
        }
        return false;
    }

    /**
     * vérification du nombre de caractères dans un nom
     * @param nom nom
     * @return boolean
     */
    private boolean verificationNomEnvoiEmail(String nom) {
        if (nom.length() >= 2 && nom.length() <= 20) {
            return true;
        }
        return false;
    }

    /**
     * suppression d'un compte membre
     */
    private void supressionDuCompte() {
        authentificationProxy.supprimerRoleUtilisateur(authentificationController.getUtilisateurAuthentifier()
                .getIdUtilisateur());
        conversationProxy.supprimerConversationsUtilisateur(authentificationController
                .getUtilisateurAuthentifier().getIdUtilisateur());
        authentificationProxy.supprimerUnUtilisateur(authentificationController.getUtilisateurAuthentifier()
                .getIdUtilisateur());
        authentificationProxy.supprimerUneAdresse(authentificationController.getUtilisateurAuthentifier()
                .getAdresseUtilisateur().getIdAdresse());
    }
}
