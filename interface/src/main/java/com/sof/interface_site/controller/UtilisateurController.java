package com.sof.interface_site.controller;

import com.sof.interface_site.model.*;
import com.sof.interface_site.proxy.MicroserviceAuthentification;
import com.sof.interface_site.proxy.MicroserviceConcert;
import com.sof.interface_site.proxy.MicroserviceInterfaceDonnees;
import com.sof.interface_site.proxy.MicroserviceNewsletterEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    private Utilisateur utilisateurAuthentifier;
    private NewsletterEmail newsletterEmail;
    private String error = null;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String accueil(Model model){
        utilisateurAuthentifier = new Utilisateur();
        newsletterEmail = new NewsletterEmail();

        interfaceModelAccueil(model, utilisateurAuthentifier);

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

        interfaceModelAccueil(model, utilisateurAuthentifier);

        if (utilisateurAuthentifier == null) {
            error = "L'email our le mot de passe est incorrect";
            return "Index";
        } else if (utilisateurAuthentifier.getRoles().get(0).getIdRole() == 2){
            return "CreationCompte";
        } else {
            return "Newsletter";
        }
    }


    @RequestMapping(value = "/ajoutEmail",method = RequestMethod.POST)
    public String ajoutEmailNewsletter(Model model, @ModelAttribute("newsletterEmail") NewsletterEmail newsletterEmailPost){

        System.out.println(newsletterEmailPost.getEmail());

        newsletterEmailProxy.ajouterEmailNewsletter(newsletterEmailPost);

        interfaceModelAccueil(model, utilisateurAuthentifier);

        return "Index";
    }

    @RequestMapping(value = "/utilisateurEnvoiEmail",method = RequestMethod.POST)
    public String envoiMailDeLUtilisateur(Model model, @RequestParam String nom, @RequestParam String email
    , @RequestParam String message){

        newsletterEmailProxy.envoyerEmailUtilisateurAAdmin(nom, email, message);

        interfaceModelAccueil(model, utilisateurAuthentifier);

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
            , BindingResult bindingResult, @ModelAttribute("adresse") @Valid Adresse adresse
            , BindingResult bindingResult1, @RequestParam String confirmationEmail
            , @RequestParam String confirmationMotDePasse){
        boolean matchRegexEmail = verificationEmail(utilisateur.getEmail());
        boolean matchRegexPassword = verificationMotDePasse(utilisateur.getMotDePasse());
        String messageErreurMail = null;
        String messageErreurMotDePasse = null;
        String messageErreurConfirmationMail = null;

        if (bindingResult.hasErrors() || bindingResult1.hasErrors() || !matchRegexEmail  || !matchRegexPassword) {
            if (!matchRegexEmail) {
                messageErreurMail = "L\'adresse mail n'est pas valable";
            }
            if (!matchRegexPassword) {
                messageErreurMotDePasse = "Le mot de passe comprend de 8 à 20 caractères et contient" +
                        " au mois une majuscule, un chiffre et un caractère spécial comme ! @ # & ( )";
            }
            if (!verificationCorrespondanceEmail(utilisateur.getEmail(), confirmationEmail)) {
                messageErreurConfirmationMail = "L\'adresse mail n'est pas valable";
            }

            interfaceModelCreationCompte(model, utilisateur, adresse, confirmationEmail, confirmationMotDePasse
                      , messageErreurMail, messageErreurMotDePasse, messageErreurConfirmationMail);

            return "CreationCompte";
        }
        interfaceModelAccueil(model, utilisateurAuthentifier);

        return "Index";
    }


    @RequestMapping(value = "/ConversationMembre", method = RequestMethod.GET)
    public String conversationMembre(Model model){
        interfaceModelAccueil(model, utilisateurAuthentifier);

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
    }

    private void interfaceModelCreationCompte(Model model, Utilisateur utilisateur, Adresse adresse) {
        PhotoInterface photoInterface = interfaceDonneesProxy.getPhotoInterface(1);

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("adresse", adresse);
        model.addAttribute("photoInterface", photoInterface);
    }

    private void interfaceModelCreationCompte(Model model, Utilisateur utilisateur, Adresse adresse
            , String confirmationEmail, String confirmationMotDePasse
            , String messageErreurMail, String messageErreurMotDePasse, String messageErreurConfirmationMail) {
        PhotoInterface photoInterface = interfaceDonneesProxy.getPhotoInterface(1);

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("adresse", adresse);
        model.addAttribute("photoInterface", photoInterface);
        model.addAttribute("confirmationEmail", confirmationEmail);
        model.addAttribute("confirmationMotDePasse", confirmationMotDePasse);
        model.addAttribute("messageErreurMail", messageErreurMail);
        model.addAttribute("messageErreurMotDePasse", messageErreurMotDePasse);
        model.addAttribute("messageErreurConfirmationMail", messageErreurConfirmationMail);
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

    /*private String envoyerErreur(Utilisateur utilisateur, Adresse adresse, BindingResult bindingResult
            , BindingResult bindingResult1, Model model, String confirmationEmail, String confirmationMotDePasse) {
        if(bindingResult.hasErrors() || bindingResult1.hasErrors() || verificationEmail(utilisateur.getEmail())
                || verificationMotDePasse(utilisateur.getMotDePasse())){
            if (verificationEmail(utilisateur.getEmail())) {
                messageErreurMail = "L\'adresse mail n'est pas valable";
                interfaceModelCreationCompte(model, utilisateur, adresse, confirmationEmail, confirmationMotDePasse
                        , messageErreurMail, null);
                return "CreationCompte";
            }

            if (verificationMotDePasse(utilisateur.getMotDePasse())) {
                messageErreurMotDePasse = "Le mot de passe doit est compris entre 8 et 20 caractères et contient" +
                        " au mois une majuscule et un caractère spécial comme ! @ # & ( )";
                interfaceModelCreationCompte(model, utilisateur, adresse, confirmationEmail, confirmationMotDePasse
                        , null, messageErreurMotDePasse);
                return "CreationCompte";
            }

            interfaceModelCreationCompte(model, utilisateur, adresse, confirmationEmail, confirmationMotDePasse
                    , null, null);

            return "CreationCompte";
        }
        return null;
    }*/

}
