package com.sof.authentification.controller;

import com.sof.authentification.dao.DaoAdresse;
import com.sof.authentification.dao.DaoUtilisateur;
import com.sof.authentification.dao.DaoUtilisateurAuthentification;
import com.sof.authentification.model.Adresse;
import com.sof.authentification.model.Utilisateur;
import com.sof.authentification.model.UtilisateurAuthentification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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



    @PostMapping(value="/Login/{motDePasse}/{email}")
    public UtilisateurAuthentification login(@PathVariable(value = "motDePasse") String motDePasse, @PathVariable(value = "email") String email ){
        UtilisateurAuthentification utilisateurAuthentification;

        List<Utilisateur> listeDesMembres = daoUtilisateur.findAll();
        utilisateurAuthentification = verificationAuthentification(listeDesMembres, motDePasse, email);

        return utilisateurAuthentification;
    }


    private UtilisateurAuthentification verificationAuthentification(List<Utilisateur> listeDesMembres, String motDePasse, String email){
        UtilisateurAuthentification utilisateurAuthentification;

        for (Utilisateur utilisateur : listeDesMembres) {
            if (utilisateur.getEmail().equals(email)) {
                if (bCryptPasswordEncoder.matches(motDePasse, utilisateur.getMotDePasse())) {
                    utilisateurAuthentification = daoUtilisateurAuthentification.findByEmail(email);
                    return utilisateurAuthentification;
                }
            }
        }
        return null;
    }
}
