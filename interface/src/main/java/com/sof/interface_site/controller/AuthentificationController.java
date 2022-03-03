package com.sof.interface_site.controller;

import com.sof.interface_site.model.*;
import com.sof.interface_site.proxy.MicroserviceAuthentification;
import com.sof.interface_site.proxy.MicroserviceConcert;
import com.sof.interface_site.proxy.MicroserviceInterfaceDonnees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AuthentificationController {

    @Autowired
    private MicroserviceInterfaceDonnees interfaceDonneesProxy;

    @Autowired
    private MicroserviceConcert concertProxy;

    @Autowired
    private MicroserviceAuthentification authentificationProxy;

    private UtilisateurAuthentification utilisateurAuthentifier;
    private Role role;
    private String erreur;
    private NewsletterEmail newsletterEmail;
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
        newsletterEmail = new NewsletterEmail();

        utilisateurAuthentifier = authentificationProxy.login(utilisateurPost);

        interfaceModelAccueil(model);

        if (utilisateurAuthentifier.getRoles().get(0).getStatut().equals("ROLE_USER")) {
            erreur = "L'email ou le mot de passe est incorrect";
            model.addAttribute("erreurAuthentification", erreur);
            erreur = null;
            return "Index";
        } else {
            return "Index";
        }
    }

    /**
     * permet de récupérer les donnéees saisies par l'utilisateur et de vérifier si l'authentification est valide
     * si elle l'est l'utilisateur est renvoyé sur la page d'accueil et le statut connecté apparait
     * dans la barre de menu ou l'admin est connecté sur sa page
     * @return la page correspondante au role
     */
    @RequestMapping(value = "/deconnexion",method = RequestMethod.GET)
    public String deconnexion(Model model){

        if (utilisateurAuthentifier != null)
        {
            utilisateurAuthentifier = null;
        }

        utilisateurAuthentifier = new UtilisateurAuthentification();
        role = new Role();
        Utilisateur utilisateur = new Utilisateur();
        newsletterEmail = new NewsletterEmail();

        role.setStatut("ROLE_USER");
        utilisateurAuthentifier.getRoles().add(0, role);

        interfaceModelAccueil(model);

        return "Index";
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
        model.addAttribute("utilisateur", new Utilisateur());
        model.addAttribute("utilisateurAuthentifier", utilisateurAuthentifier);
    }

    public UtilisateurAuthentification getUtilisateurAuthentifier() {
        return this.utilisateurAuthentifier;
    }

}
