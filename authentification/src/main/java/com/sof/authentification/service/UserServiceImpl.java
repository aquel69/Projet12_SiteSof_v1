package com.sof.authentification.service;

import com.sof.authentification.dao.DaoAdresse;
import com.sof.authentification.dao.DaoRole;
import com.sof.authentification.dao.DaoUtilisateur;
import com.sof.authentification.model.Adresse;
import com.sof.authentification.model.Role;
import com.sof.authentification.model.Utilisateur;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    DaoUtilisateur daoUtilisateur;

    @Autowired
    DaoAdresse daoAdresse;

    @Autowired
    DaoRole daoRole;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * ajoute un utilisateur avec un mot de passe crypté dans la base de données
     * @param utilisateur utilisateur
     * @return Utilisateur
     */
    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));

        return daoUtilisateur.save(utilisateur);
    }

    /**
     * modifie un utilisateur dans la base de données
     * @param utilisateur utilisateur
     * @return Utilisateur
     */
    @Override
    public Utilisateur modifierUtilisateur(Utilisateur utilisateur) {
        return daoUtilisateur.save(utilisateur);
    }

    /**
     * modifie une adresse dans la base de données
     * @param adresse adresse
     * @return Adresse
     */
    @Override
    public Adresse modifierAdresse(Adresse adresse) {
        return daoAdresse.save(adresse);
    }

    /**
     * récupère un utilisateur selon son username dans la base de données
     * @param username username
     * @return Utilisateur
     */
    @Override
    public Utilisateur findUtilisateurByUsername(String username) {
        return daoUtilisateur.findByUsername(username);
    }

    /**
     * ajoute une adresse dans la base de données
     * @param adresse adresse
     * @return Adresse
     */
    @Override
    public Adresse addAdresse(Adresse adresse){
        return daoAdresse.save(adresse);
    }

    /**
     * ajoute un role au membre
     * @param username username
     * @return Utilisateur
     */
    @Override
    public Utilisateur addRoleToUtilisateur(String username) {
        Utilisateur utilisateur = daoUtilisateur.findByUsername(username);
        Role role = daoRole.findByStatut("ROLE_MEMBER");

        utilisateur.getRoles().add(0, role);

        daoUtilisateur.save(utilisateur);

        return utilisateur;
    }


}
