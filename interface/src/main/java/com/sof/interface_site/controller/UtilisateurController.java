package com.sof.interface_site.controller;

import com.sof.interface_site.model.*;
import com.sof.interface_site.proxy.MicroserviceAuthentification;
import com.sof.interface_site.proxy.MicroserviceConcert;
import com.sof.interface_site.proxy.MicroserviceInterfaceDonnees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UtilisateurController {

    @Autowired
    private MicroserviceAuthentification authentificationProxy;

    @Autowired
    private MicroserviceInterfaceDonnees interfaceDonneesProxy;

    @Autowired
    private MicroserviceConcert concertProxy;

    private Utilisateur utilisateurAuthentifier;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String accueil(Model model, @ModelAttribute("utilisateur") Utilisateur utilisateurGet){
        utilisateurAuthentifier = new Utilisateur();

        interfaceSite(model, utilisateurAuthentifier);

        System.out.println("Accueil");
        return "Index";
    }


    /**
     * permet de récupérer les donnéees saisies par l'utilisateur et de vérifier si l'authentification est valide
     * si elle l'est l'utilisateur est renvoyé sur la page d'accueil sinon il reste sur la page authentification
     * @param model
     * @param utilisateurPost
     * @return la page correspondante au role
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String validationAuthentification(Model model, @ModelAttribute("utilisateur") UtilisateurAuth utilisateurPost){

        utilisateurAuthentifier = authentificationProxy.login(utilisateurPost);

        interfaceSite(model, utilisateurAuthentifier);

        System.out.println(utilisateurAuthentifier);
        if (utilisateurAuthentifier == null) {
            return "Index";
        } else if (utilisateurAuthentifier.getRoles().get(0).getIdRole() == 2){
            return "CreationCompte";
        } else {
            return "Newsletter";
        }
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
    }



}
