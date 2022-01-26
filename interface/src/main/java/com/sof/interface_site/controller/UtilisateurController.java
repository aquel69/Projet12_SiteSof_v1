package com.sof.interface_site.controller;

import com.sof.interface_site.model.*;
import com.sof.interface_site.proxy.MicroserviceAuthentification;
import com.sof.interface_site.proxy.MicroserviceConcert;
import com.sof.interface_site.proxy.MicroserviceInterfaceDonnees;
import com.sof.interface_site.proxy.MicroserviceNewsletterEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UtilisateurController {

    private final String EMAIL_PATTERN ="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
    private final Pattern patternEmail = Pattern.compile(EMAIL_PATTERN);
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

        interfaceSite(model, utilisateurAuthentifier);

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

        interfaceSite(model, utilisateurAuthentifier);

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

        interfaceSite(model, utilisateurAuthentifier);

        return "Index";

    }

    @RequestMapping(value = "/CreationCompte", method = RequestMethod.GET)
    public String inscription(Model model){
        interfaceSite(model, utilisateurAuthentifier);

        System.out.println("CreationCompte");
        return "CreationCompte";
    }

    @RequestMapping(value = "/ConversationMembre", method = RequestMethod.GET)
    public String conversationMembre(Model model){
        interfaceSite(model, utilisateurAuthentifier);

        System.out.println("ConversationMembre");
        return "ConversationMembre";
    }

    private void interfaceSite(Model model, Utilisateur utilisateur) {

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

    private boolean verificationEmail(String email) {
        Matcher matcher = patternEmail.matcher(email);
        return matcher.matches();
    }

    private boolean verificationMotDePasse(String motDePasse) {
        Matcher matcher = patternPassword.matcher(motDePasse);
        return matcher.matches();
    }

}
