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
@Table(name=("adresse"))
public class Adresse {

    /**
     * id du de la table adresse
     */
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="adresse_id_seq_1", initialValue = 1, allocationSize = 1)
    @Column(name="id_adresse")
    private int idAdresse;

    /**
     * adresse
     */
    @NonNull
    @Column(name="adresse")
    private String adresse;

    /**
     * complément de l'adresse
     */
    @Column(name="complement_adresse")
    private String complementAdresse;

    /**
     * code postal de la ville
     */
    @NonNull
    @Column(name="code_postal")
    private String codePostal;

    /**
     * ville
     */
    @NonNull
    @Column(name="ville")
    private String ville;

}
