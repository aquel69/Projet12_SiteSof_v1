package com.sof.authentification.service;

import com.sof.authentification.dao.DaoAdresse;
import com.sof.authentification.dao.DaoRole;
import com.sof.authentification.dao.DaoUtilisateur;
import com.sof.authentification.model.Adresse;
import com.sof.authentification.model.Role;
import com.sof.authentification.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        //Adresse adresse = daoAdresse.findByIdAdresse(daoAdresse.recupererDernierAdresse());
        utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));
        //utilisateur.setAdresseUtilisateur(adresse);

        return daoUtilisateur.save(utilisateur);
    }

    @Override
    public Utilisateur modifierUtilisateur(Utilisateur utilisateur) {
        return daoUtilisateur.save(utilisateur);
    }

    @Override
    public Adresse modifierAdresse(Adresse adresse) {
        return daoAdresse.save(adresse);
    }

    @Override
    public Utilisateur findUtilisateurByUsername(String username) {
        return daoUtilisateur.findByUsername(username);
    }

    @Override
    public Adresse addAdresse(Adresse adresse){
        return daoAdresse.save(adresse);
    }

    @Override
    public Utilisateur addRoleToUtilisateur(String username) {
        Utilisateur utilisateur = daoUtilisateur.findByUsername(username);
        Role role = daoRole.findByStatut("ROLE_MEMBER");

        utilisateur.getRoles().add(0, role);

        daoUtilisateur.save(utilisateur);

        return utilisateur;
    }


}
