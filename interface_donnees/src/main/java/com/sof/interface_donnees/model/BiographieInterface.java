package com.sof.interface_donnees.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name=("biographie_interface"))
public class BiographieInterface {

    /**
     * id de la table biographie_interface
     */
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="adresse_id_seq_1", initialValue = 1, allocationSize = 1)
    @Column(name="id_biographie_interface")
    private int idBiographieInterface;

    /**
     * 1er sous titre
     */
    @NonNull
    @Column(name="sous_titre_1")
    private String sousTitre1;

    /**
     * 1er paragraphe
     */
    @NonNull
    @Column(name="paragraphe_1")
    private String paragraphe1;

    /**
     * 2ème sous titre
     */
    @NonNull
    @Column(name="sous_titre_2")
    private String sousTitre2;

    /**
     * 2ème paragraphe
     */
    @NonNull
    @Column(name="paragraphe_2")
    private String paragraphe2;

    /**
     * photo 1
     */
    @NonNull
    @Column(name="photo_1")
    private String photo1;

    /**
     * photo 2
     */
    @NonNull
    @Column(name="photo_2")
    private String photo2;

    /**
     * photo 3
     */
    @NonNull
    @Column(name="photo_3")
    private String photo3;

}
