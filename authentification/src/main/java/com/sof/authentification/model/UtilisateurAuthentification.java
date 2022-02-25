package com.sof.authentification.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name=("utilisateur"))
public class UtilisateurAuthentification {

    /**
     * id de la table membre
     */
    @Id
    @Column(name="id_utilisateur")
    private int idUtilisateur;

    /**
     * nom de l'utilisateur
     */
    @NonNull
    @Column(name="nom")
    private String nom;

    /**
     * prénom de l'utilisateur
     */
    @NonNull
    @Column(name="prenom")
    private String prenom;

    /**
     * username de l'utilisateur
     */
    @NonNull
    @Column(name="username")
    private String username;

    /**
     * adresse de l'utilisateur
     */
    @NonNull
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="adresse_id")
    private Adresse adresseUtilisateur;

    /**
     * email du membre
     */
    @NonNull
    @Column(name="email")
    private String email;

    /**
     * role de l'utilisateur
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "utilisateur_role", joinColumns = @JoinColumn (name = "utilisateur_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
