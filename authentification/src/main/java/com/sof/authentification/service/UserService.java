package com.sof.authentification.service;

import com.sof.authentification.model.Adresse;
import com.sof.authentification.model.Utilisateur;

public interface UserService {

    Utilisateur saveUtilisateur(Utilisateur utilisateur);
    Utilisateur findUtilisateurByEmail(String email);
    Adresse addAdresse(Adresse adresse);

}
