package com.sof.interface_donnees.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name=("accueil_interface"))
public class AccueilInterface {

    /**
     * lien de la video youtube de l'accueil et id de la table accueil_interface
     */
    @Id
    @Column(name="adresse")
    private String urlVideoYoutube;

}
