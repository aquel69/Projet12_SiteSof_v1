package com.sof.authentification.service;

import com.sof.authentification.dao.DaoAdresse;
import com.sof.authentification.dao.DaoUtilisateur;
import com.sof.authentification.model.Adresse;
import com.sof.authentification.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    DaoUtilisateur daoUtilisateur;

    @Autowired
    DaoAdresse daoAdresse;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        Adresse adresse = daoAdresse.findByIdAdresse(daoAdresse.recupererDernierAdresse());
        utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));
        utilisateur.setAdresse(adresse);

        return daoUtilisateur.save(utilisateur);
    }

    @Override
    public Utilisateur findUtilisateurByEmail(String email) {
        return daoUtilisateur.findByEmail(email);
    }

    @Override
    public Adresse addAdresse(Adresse adresse){
        return daoAdresse.save(adresse);
    }


}
