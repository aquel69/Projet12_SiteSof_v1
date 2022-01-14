package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Utilisateur {

    /**
     * id de la table membre
     */
    private int idMembre;

    /**
     * nom du membre
     */
    private String nom;

    /**
     * prénom du membre
     */
    private String prenom;

    /**
     * username de l'utilisateur
     */
    private String username;

    /**
     * adresse du memebre
     */
    private Adresse adresse;

    /**
     * email du membre
     */
    private String email;

    /**
     * mot de passe du membre
     */
    private String motDePasse;

    /**
     * date de création du compte
     */
    private LocalDateTime dateAjout;


    /**
     * role de l'utilisateur
     */
    private int role;
}
