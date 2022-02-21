package com.sof.authentification.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name=("utilisateur"))
public class Utilisateur {

    /**
     * id de la table utilisateur
     */
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="membre_id_seq", initialValue = 1, allocationSize = 1)
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
     * email de l'utilisateur
     */
    @NonNull
    @Column(name="email")
    private String email;

    /**
     * mot de passe de l'utilisateur
     */
    @NonNull
    @Column(name="mot_de_passe")
    private String motDePasse;

    /**
     * date de création du compte
     */
    @NonNull
    @Column(name="date_de_creation_du_compte", columnDefinition = "TIMESTAMP")
    private LocalDateTime dateAjout;

    /**
     * role de l'utilisateur
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "utilisateur_role", joinColumns = @JoinColumn (name = "utilisateur_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
