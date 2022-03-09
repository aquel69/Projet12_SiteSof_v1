package com.sof.authentification.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name=("utilisateur_role"))
public class UtilisateurRole {

    @Id
    @Column(name="id_utilisateur_role")
    private int idUtilisateur;

    @Column(name="utilisateur_id")
    private int utilisateurId;

    @Column(name="role_id")
    private int roleId;

}
