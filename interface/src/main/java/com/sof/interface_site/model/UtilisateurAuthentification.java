package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UtilisateurAuthentification {

    public UtilisateurAuthentification(Adresse adresse){
        adresse = this.adresseUtilisateur;
    }

    private int idUtilisateur;

    /**
     * email du membre
     */
    private String email;

    /**
     * username de l'utilisateur
     */
    private String username;

    /**
     * nom de l'utilisateur
     */
    private String nom;

    /**
     * prénom de l'utilisateur
     */
    private String prenom;

    /**
     * adresse de l'utilisateur
     */
    private Adresse adresseUtilisateur;

    /**
     * role de l'utilisateur
     */
    private List<Role> roles = new ArrayList<>();
}
