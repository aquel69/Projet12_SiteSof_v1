package com.sof.authentification.service;

import com.sof.authentification.model.Adresse;
import com.sof.authentification.model.Utilisateur;

import java.util.List;

public interface UserService {

    Utilisateur saveUtilisateur(Utilisateur utilisateur);
    Utilisateur findUtilisateurByUsername(String username);
    Adresse addAdresse(Adresse adresse);
    Utilisateur addRoleToUtilisateur(String email);

}
