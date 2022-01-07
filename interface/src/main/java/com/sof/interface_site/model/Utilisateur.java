package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

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
