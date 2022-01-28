package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlbumInterface {

    /**
     * id de la table album_interface
     */
    private int idAlbumInterface;

    /**
     * 1er sous titre
     */
    private String sousTitre1;

    /**
     * 2ème sous titre
     */
    private String sousTitre2;

    /**
     * photo 1
     */
    private String photoAlbum;

    /**
     * photo 2
     */
    private String photoCD;

    /**
     * 1er lien chanson
     */
    private String chanson1;

    /**
     * 2ème lien chanson
     */
    private String chanson2;

    /**
     * 3ème lien chanson
     */
    private String chanson3;

    /**
     * 4ème lien chanson
     */
    private String chanson4;

    /**
     * 1er titre chanson
     */
    private String titreChanson1;

    /**
     * 2ème titre chanson
     */
    private String titreChanson2;

    /**
     * 3ème titre chanson
     */
    private String titreChanson3;

    /**
     * 4ème titre chanson
     */
    private String titreChanson4;

    /**
     * tarif de l'EP
     */
    private String tarifEP;

}
