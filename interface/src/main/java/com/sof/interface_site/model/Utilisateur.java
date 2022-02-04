package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class Utilisateur {

    /**
     * id de la table membre
     */
    private int idUtilisateur;

    /**
     * nom du membre
     */
    @Size(min=2, max = 20, message = "Le nom doit être compris entre 2 et 20 caractères")
    private String nom;

    /**
     * prénom du membre
     */
    @Size(min=2, max = 20, message = "Le prénom doit être compris entre 2 et 20 caractères")
    private String prenom;

    /**
     * username de l'utilisateur
     */
    @Size(min=2, max = 20, message = "Le nom d'utilisateur doit être compris entre 2 et 15 caractères")
    private String username;

    /**
     * adresse du membre
     */
    private Adresse adresseUtilisateur;

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
    private List<Role> roles;
}
