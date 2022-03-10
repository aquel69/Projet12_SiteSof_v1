package com.sof.authentification.model;

import lombok.Data;

@Data
public class UtilisateurAuth {

    /**
     * username
     */
    private String username;

    /**
     * mot de passe
     */
    private String motDePasse;

}
