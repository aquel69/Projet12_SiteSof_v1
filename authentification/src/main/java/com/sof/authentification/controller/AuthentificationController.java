package com.sof.authentification.controller;

import com.sof.authentification.Security.MyUserDetailService;
import com.sof.authentification.dao.DaoAdresse;
import com.sof.authentification.dao.DaoRole;
import com.sof.authentification.dao.DaoUtilisateur;
import com.sof.authentification.dao.DaoUtilisateurAuthentification;
import com.sof.authentification.model.Adresse;
import com.sof.authentification.model.Role;
import com.sof.authentification.model.Utilisateur;
import com.sof.authentification.model.UtilisateurAuth;
import com.sof.authentification.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
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
    UserServiceImpl userService;

    @Autowired
    DaoRole daoRole;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    MyUserDetailService myUserDetailService;

    private Adresse adresseSave;


    /**
     * ajouter une adresse dans la base de données
     * @param adresse
     * @return le nombre de lignes ajoutées
     */
    @PostMapping(value = "/ajouterAdresse")
    public Adresse addAdresse(@RequestBody  Adresse adresse){
        return userService.addAdresse(adresse);
    }

    /**
     * ajouter un utilisateur dans la base de données
     * @param utilisateur
     * @return le nombre de lignes ajoutées
     */
    @PostMapping(value = "/ajouterUtilisateur")
    public Utilisateur saveUtilisateur(@RequestBody Utilisateur utilisateur) {
        /*utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));
        utilisateur.setDateAjout(LocalDateTime.now());
        Role role = findRoleByStatut("ROLE_MEMBER");

        utilisateur.getRoles().add(role);*/
        return userService.saveUtilisateur(utilisateur);
    }

    @GetMapping(value = "/roleSelonStatut/{statut}")
    public Role findRoleByStatut(@PathVariable String statut) {
        Role role = daoRole.findByStatut(statut);

        return role;
    }


    @PostMapping(value = "/ajouterRole")
    public Utilisateur saveRole(@RequestBody Utilisateur utilisateur) {

        return userService.addRoleToUtilisateur(utilisateur.getUsername());
    }


    @GetMapping(value = "/utilisateurSelonUsername/{username}")
    public Utilisateur findUtilisateurByUsername(@PathVariable String username) {
        Utilisateur utilisateur = daoUtilisateur.findByUsername(username);
        System.out.println(utilisateur.toString());

        return utilisateur;
    }

    @GetMapping(value = "/tousLesUtilisateurs")
    public List<Utilisateur> findAllUtilisateur() {
        List<Utilisateur> utilisateurs = daoUtilisateur.findAll();

        return utilisateurs;
    }

    @GetMapping(value = "/derniereAdresse")
    public int recupererDernierAdresse(){
        int idDerniereAdresse = daoAdresse.recupererDernierAdresse();

        return idDerniereAdresse;
    }

    @GetMapping(value = "/derniereUtilisateur")
    public int recupererDernierUtilisateur(){
        int idDerniereUtilisateur = daoUtilisateur.recupererDernierUtilisateur();

        return idDerniereUtilisateur;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
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
