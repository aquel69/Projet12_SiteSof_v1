package com.sof.authentification.controller;

import com.sof.authentification.Security.MyUserDetailService;
import com.sof.authentification.dao.DaoAdresse;
import com.sof.authentification.dao.DaoUtilisateur;
import com.sof.authentification.dao.DaoUtilisateurAuthentification;
import com.sof.authentification.model.Adresse;
import com.sof.authentification.model.Utilisateur;
import com.sof.authentification.model.UtilisateurAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AuthentificationController {

    @Autowired
    DaoUtilisateur daoUtilisateur;

    @Autowired
    DaoUtilisateurAuthentification daoUtilisateurAuthentification;

    @Autowired
    DaoAdresse daoAdresse;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    MyUserDetailService myUserDetailService;


    /**
     * ajouter une adresse dans la base de données
     * @param adresse
     * @return le nombre de lignes ajoutées
     */
    @PostMapping(value = "/AjouterAdresse")
    public Adresse addAdresse(Adresse adresse){
        return daoAdresse.save(adresse);
    }

    /**
     * ajouter un utilisateur dans la base de données
     * @param utilisateur
     * @return le nombre de lignes ajoutées
     */
    @PostMapping(value = "/AjouterUtilisateur")
    public Utilisateur saveUtilisateur(@RequestBody Utilisateur utilisateur) {
        Adresse adresse = daoAdresse.findByIdAdresse(daoAdresse.recupererDernierAdresse());
        utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));
        utilisateur.setAdresse(adresse);

        return daoUtilisateur.save(utilisateur);
    }

    @GetMapping(value = "/UtilisateurSelonEmail/{email}")
    public Utilisateur findUtilisateurByEmail(@PathVariable String email) {
        Utilisateur utilisateur = daoUtilisateur.findByEmail(email);
        System.out.println(utilisateur.toString());

        return utilisateur;
    }

    @GetMapping(value = "/TousLesUtilisateurs")
    public List<Utilisateur> findAllUtilisateur() {
        List<Utilisateur> utilisateurs = daoUtilisateur.findAll();

        return utilisateurs;
    }

    @GetMapping(value = "/DerniereAdresse")
    public int recupererDernierAdresse(){
        int idDerniereAdresse = daoAdresse.recupererDernierAdresse();

        return idDerniereAdresse;
    }

    @PostMapping(value = "/Login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Utilisateur login(@Valid @RequestBody UtilisateurAuth utilisateurAuth) {
        List<Utilisateur> listeDesMembres = daoUtilisateur.findAll();
        System.out.println(utilisateurAuth);
        Utilisateur utilisateurAuthentifie = verificationAuthentification(listeDesMembres, utilisateurAuth.getMotDePasse()
                , utilisateurAuth.getUsername());

        return utilisateurAuthentifie;
    }

    private Utilisateur verificationAuthentification(List<Utilisateur> listeDesMembres, String motDePasse, String username){
        for (Utilisateur utilisateur : listeDesMembres) {
            if (utilisateur.getUsername().equals(username)) {
                if (bCryptPasswordEncoder.matches(motDePasse, utilisateur.getMotDePasse())) {
                    utilisateur = daoUtilisateur.findByUsername(username);

                    return utilisateur;
                }
            }
        }
        return null;
    }
}
