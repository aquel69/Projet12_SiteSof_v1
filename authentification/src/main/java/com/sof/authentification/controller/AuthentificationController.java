package com.sof.authentification.controller;

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

    /**
     * ajouter une adresse dans la base de données
     * @param adresse adresse
     * @return Adresse
     */
    @PostMapping(value = "/ajouterAdresse")
    public Adresse addAdresse(@RequestBody  Adresse adresse){
        return userService.addAdresse(adresse);
    }

    /**
     * ajouter un utilisateur dans la base de données
     * @param utilisateur utilisateur
     * @return Utilisateur
     */
    @PostMapping(value = "/ajouterUtilisateur")
    public Utilisateur saveUtilisateur(@RequestBody Utilisateur utilisateur) {
        return userService.saveUtilisateur(utilisateur);
    }

    /**
     * ajouter un rôle dans la base de données
     * @param utilisateur utilisateur
     * @return Utilisateur
     */
    @PostMapping(value = "/ajouterRole")
    public Utilisateur saveRole(@RequestBody Utilisateur utilisateur) {

        return userService.addRoleToUtilisateur(utilisateur.getUsername());
    }

    /**
     * modifier un membre dans la base de données
     * @param utilisateur utilisateur
     * @return Utilisateur
     */
    @PutMapping(value="/modifierMembre")
    public Utilisateur modifierMembre(@RequestBody Utilisateur utilisateur) {
       return userService.modifierUtilisateur(utilisateur);
    }

    /**
     * modifier une adresse dans la base de données
     * @param adresse adresse
     * @return Adresse
     */
    @PutMapping(value="/modifierAdresse")
    public Adresse modifierAdresse(@RequestBody Adresse adresse) {
        return userService.modifierAdresse(adresse);
    }

    /**
     * Supprime un utilisateur de la base de données
     * @param idUtilisateur idUtilisateur
     * @return boolean
     */
    @DeleteMapping(value="/supprimerUnUtilisateur/{idUtilisateur}")
    public boolean supprimerUnUtilisateur(@PathVariable int idUtilisateur) {
        daoUtilisateur.deleteById(idUtilisateur);

        return true;
    }

    /**
     * Supprime une adresse de la base de données
     * @param idAdresse idAdresse
     * @return boolean
     */
    @DeleteMapping(value="/supprimerUneAdresse/{idAdresse}")
    public boolean supprimerUneAdresse(@PathVariable int idAdresse) {
        daoAdresse.deleteById(idAdresse);

        return true;
    }

    /**
     * Supprime un Role de la base de données
     * @param idMembre idMembre
     * @return boolean
     */
    @DeleteMapping(value="/supprimerRoleUtilisateur/{idMembre}")
    public boolean supprimerRoleUtilisateur(@PathVariable int idMembre) {
        daoUtilisateurRole.supprimerRoleUtilisateur(idMembre);

        return true;
    }

    /**
     * récupère un role selon son statut dans la base de données
     * @param statut statut
     * @return Role
     */
    @GetMapping(value = "/roleSelonStatut/{statut}")
    public Role findRoleByStatut(@PathVariable String statut) {
        Role role = daoRole.findByStatut(statut);

        return role;
    }

    /**
     * récupère une liste de role selon son utilisateur dans la base de données
     * @param idUtilisateur idUtilisateur
     * @return List String
     */
    @GetMapping(value = "/userInfo/listeDesRolesSelonUtilisateur/{idUtilisateur}")
    public List<String> listeDesRolesSelonUtilisateur(@PathVariable int idUtilisateur) {
        List<String> listeRoles = daoRole.listeDesRolesDeLUtilisateur(idUtilisateur);

        return listeRoles;
    }

    /**
     * récupère un utilisateurAuthentification selon son username dans la base de données
     * @param username username
     * @return UtilisateurAuthentification
     */
    @GetMapping(value = "/utilisateurAuthentificationSelonUsername/{username}")
    public UtilisateurAuthentification findUtilisateurAuthentificationByUsername(@PathVariable String username) {
        UtilisateurAuthentification utilisateurAuthentification = daoUtilisateurAuthentification.findByUsername(username);

        return utilisateurAuthentification;
    }

    /**
     * récupère un utilisateur selon son username dans la base de données
     * @param username username
     * @return Utilisateur
     */
    @GetMapping(value = "/utilisateurSelonUsername/{username}")
    public Utilisateur findUtilisateurByUsername(@PathVariable String username) {
        Utilisateur utilisateur = daoUtilisateur.findByUsername(username);

        return utilisateur;
    }

    /**
     * récupère un utilisateurAuthentification selon son id dans la base de données
     * @param id id
     * @return UtilisateurAuthentification
     */
    @GetMapping(value = "/utilisateurSelonId/{id}")
    public UtilisateurAuthentification findUtilisateurById(@PathVariable int id) {
        UtilisateurAuthentification utilisateurAuthentification = daoUtilisateurAuthentification.findById(id);

        return utilisateurAuthentification;
    }

    /**
     * récupère une liste de tous les utilisateurs dans la base de données
     * @return List Utilisateur
     */
    @GetMapping(value = "/tousLesUtilisateurs")
    public List<Utilisateur> findAllUtilisateur() {
        List<Utilisateur> utilisateurs = daoUtilisateur.findAll();

        return utilisateurs;
    }

    /**
     * récupère une adresse selon son id dans la base de données
     * @param id id
     * @return Adresse
     */
    @GetMapping(value = "/adresseSelonId/{id}")
    public Adresse adresseSelonId(@PathVariable int id){
        Adresse adresse = daoAdresse.adresseSelonId(id);

        return adresse;
    }

    /**
     * récupère l'id de la dernière adresse ajoutée dans la base de données
     * @return int
     */
    @GetMapping(value = "/derniereAdresse")
    public int recupererDernierAdresse(){
        int idDerniereAdresse = daoAdresse.recupererDernierAdresse();

        return idDerniereAdresse;
    }

    /**
     * récupère l'id du dernier utilisateur ajouté dans la base de données
     * @return int
     */
    @GetMapping(value = "/derniereUtilisateur")
    public int recupererDernierUtilisateur(){
        int idDerniereUtilisateur = daoUtilisateur.recupererDernierUtilisateur();

        return idDerniereUtilisateur;
    }

    /**
     * permet d'authentifier un utilisateur
     * @param utilisateurAuth utilisateurAuth
     * @return UtilisateurAuthentification
     */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UtilisateurAuthentification login(@Valid @RequestBody UtilisateurAuth utilisateurAuth) {
        List<Utilisateur> listeDesMembres = daoUtilisateur.findAll();
        UtilisateurAuthentification utilisateurAuthentifier = verificationAuthentification(listeDesMembres, utilisateurAuth.getMotDePasse()
                , utilisateurAuth.getUsername());

        return utilisateurAuthentifier;
    }

    /**
     * verification de la correspondance des mots de passe
     * @param listeDesMembres listeDesMembres
     * @param motDePasse motDePasse
     * @param username username
     * @return UtilisateurAuthentification
     */
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
