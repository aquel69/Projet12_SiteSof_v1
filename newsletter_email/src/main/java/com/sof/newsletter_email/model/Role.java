package com.sof.newsletter_email.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name=("role"))
public class Role {

    /**
     * id de la table utilisateur
     */
    @Id
    @Column(name="id_role")
    private int idRole;

    /**
     * statut du role
     */
    @NonNull
    @Column(name="statut")
    private String statut;

}
