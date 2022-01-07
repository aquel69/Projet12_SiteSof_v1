package com.sof.authentification.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name=("utilisateur"))
public class Utilisateur {

    /**
     * id de la table membre
     */
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="membre_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name="id_utilisateur")
    private int idUtilisateur;

    /**
     * nom du membre
     */
    @NonNull
    @Column(name="nom")
    private String nom;

    /**
     * prénom du membre
     */
    @NonNull
    @Column(name="prenom")
    private String prenom;

    /**
     * adresse du memebre
     */
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="id_adresse")
    private Adresse adresse;

    /**
     * email du membre
     */
    @NonNull
    @Column(name="email")
    private String email;

    /**
     * mot de passe du membre
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
    @NonNull
    @Column(name="role")
    private int role;
}
