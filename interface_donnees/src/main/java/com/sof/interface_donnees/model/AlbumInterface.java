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
@Table(name=("album_interface"))
public class AlbumInterface {

    /**
     * id de la table album_interface
     */
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="adresse_id_seq_1", initialValue = 1, allocationSize = 1)
    @Column(name="id_album_interface")
    private int idAlbumInterface;

    /**
     * 1er sous titre
     */
    @NonNull
    @Column(name="sous_titre_1")
    private String sousTitre1;

    /**
     * 2ème sous titre
     */
    @NonNull
    @Column(name="sous_titre_2")
    private String sousTitre2;

    /**
     * photo 1
     */
    @NonNull
    @Column(name="photo_album")
    private String photoAlbum;

    /**
     * photo 2
     */
    @NonNull
    @Column(name="photo_cd")
    private String photoCD;

    /**
     * 1er lien chanson
     */
    @NonNull
    @Column(name="chanson_1")
    private String chanson1;

    /**
     * 2ème lien chanson
     */
    @NonNull
    @Column(name="chanson_2")
    private String chanson2;

    /**
     * 3ème lien chanson
     */
    @NonNull
    @Column(name="chanson_3")
    private String chanson3;

    /**
     * 4ème lien chanson
     */
    @NonNull
    @Column(name="chanson_4")
    private String chanson4;

    /**
     * 1er titre chanson
     */
    @NonNull
    @Column(name="titre_chanson_1")
    private String titreChanson1;

    /**
     * 2ème titre chanson
     */
    @NonNull
    @Column(name="titre_chanson_2")
    private String titreChanson2;

    /**
     * 3ème titre chanson
     */
    @NonNull
    @Column(name="titre_chanson_3")
    private String titreChanson3;

    /**
     * 4ème titre chanson
     */
    @NonNull
    @Column(name="titre_chanson_4")
    private String titreChanson4;

    /**
     * tarif de l'EP
     */
    @NonNull
    @Column(name="tarif_ep")
    private String tarifEP;

}
