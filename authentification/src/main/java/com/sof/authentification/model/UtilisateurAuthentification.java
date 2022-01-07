package com.sof.authentification.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Column;



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
    private int idMembre;

    /**
     * email du membre
     */
    @NonNull
    @Column(name="email")
    private String email;

    /**
     * role de l'utilisateur
     */
    @NonNull
    @Column(name="role")
    private int role;
}
