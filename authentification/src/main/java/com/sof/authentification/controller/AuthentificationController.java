package com.sof.authentification.controller;

import com.sof.authentification.Security.MyUserDetailService;
import com.sof.authentification.dao.*;
import com.sof.authentification.model.*;
import com.sof.authentification.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    DaoUtilisateurRole daoUtilisateurRole;

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
        return userService.saveUtilisateur(utilisateur);
    }

    /**
     * modifier un membre dans la base de données
     * @param utilisateur
     */
    @PutMapping(value="/modifierMembre")
    public Utilisateur modifierMembre(@RequestBody Utilisateur utilisateur) {
       return userService.modifierUtilisateur(utilisateur);
    }
    /**
     * modifier une adresse dans la base de données
     * @param adresse
     */
    @PutMapping(value="/modifierAdresse")
    public Adresse modifierAdresse(@RequestBody Adresse adresse) {
        return userService.modifierAdresse(adresse);
    }


    @DeleteMapping(value="/supprimerUnUtilisateur/{idUtilisateur}")
    public boolean supprimerUnUtilisateur(@PathVariable int idUtilisateur) {
        daoUtilisateur.deleteById(idUtilisateur);

        return true;
    }

    @DeleteMapping(value="/supprimerUneAdresse/{idAdresse}")
    public boolean supprimerUneAdresse(@PathVariable int idAdresse) {
        daoAdresse.deleteById(idAdresse);

        return true;
    }

    @DeleteMapping(value="/supprimerRoleUtilisateur/{idMembre}")
    public boolean supprimerRoleUtilisateur(@PathVariable int idMembre) {
        daoUtilisateurRole.supprimerRoleUtilisateur(idMembre);

        return true;
    }

    @GetMapping(value = "/roleSelonStatut/{statut}")
    public Role findRoleByStatut(@PathVariable String statut) {
        Role role = daoRole.findByStatut(statut);

        return role;
    }

    @GetMapping(value = "/userInfo/listeDesRolesSelonUtilisateur/{idUtilisateur}")
    public List<String> listeDesRolesSelonUtilisateur(@PathVariable int idUtilisateur) {
        List<String> listeRoles = daoRole.listeDesRolesDeLUtilisateur(idUtilisateur);

        return listeRoles;
    }

    @PostMapping(value = "/ajouterRole")
    public Utilisateur saveRole(@RequestBody Utilisateur utilisateur) {

        return userService.addRoleToUtilisateur(utilisateur.getUsername());
    }


    @GetMapping(value = "/utilisateurAuthentificationSelonUsername/{username}")
    public UtilisateurAuthentification findUtilisateurAuthentificationByUsername(@PathVariable String username) {
        UtilisateurAuthentification utilisateurAuthentification = daoUtilisateurAuthentification.findByUsername(username);

        return utilisateurAuthentification;
    }

    @GetMapping(value = "/utilisateurSelonUsername/{username}")
    public Utilisateur findUtilisateurByUsername(@PathVariable String username) {
        Utilisateur utilisateur = daoUtilisateur.findByUsername(username);

        return utilisateur;
    }

    @GetMapping(value = "/utilisateurSelonId/{id}")
    public UtilisateurAuthentification findUtilisateurById(@PathVariable int id) {
        UtilisateurAuthentification utilisateurAuthentification = daoUtilisateurAuthentification.findById(id);

        return utilisateurAuthentification;
    }

    @GetMapping(value = "/tousLesUtilisateurs")
    public List<Utilisateur> findAllUtilisateur() {
        List<Utilisateur> utilisateurs = daoUtilisateur.findAll();

        return utilisateurs;
    }

    @GetMapping(value = "/adresseSelonId/{id}")
    public Adresse adresseSelonId(@PathVariable int id){
        Adresse adresse = daoAdresse.adresseSelonId(id);

        return adresse;
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
    public UtilisateurAuthentification login(@Valid @RequestBody UtilisateurAuth utilisateurAuth) {
        List<Utilisateur> listeDesMembres = daoUtilisateur.findAll();
        UtilisateurAuthentification utilisateurAuthentifier = verificationAuthentification(listeDesMembres, utilisateurAuth.getMotDePasse()
                , utilisateurAuth.getUsername());

        return utilisateurAuthentifier;
    }

    private UtilisateurAuthentification verificationAuthentification(List<Utilisateur> listeDesMembres, String motDePasse, String username){
        UtilisateurAuthentification utilisateurAuthentification = new UtilisateurAuthentification();
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        for (Utilisateur utilisateur : listeDesMembres) {
            if (utilisateur.getUsername().equals(username)) {
                if (bCryptPasswordEncoder.matches(motDePasse, utilisateur.getMotDePasse())) {
                    utilisateurAuthentification = daoUtilisateurAuthentification.findByUsername(username);

                    return utilisateurAuthentification;
                }
            }
        }
        role.setStatut("ROLE_USER");
        roles.add(role);
        utilisateurAuthentification.setRoles(roles);

        return utilisateurAuthentification;
    }

}
