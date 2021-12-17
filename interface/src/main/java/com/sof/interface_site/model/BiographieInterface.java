package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BiographieInterface {

    /**
     * id de la table biographie_interface
     */
    private int idBiographieInterface;

    /**
     * 1er sous titre
     */
    private String sousTitre1;

    /**
     * 1er paragraphe
     */
    private String paragraphe1;

    /**
     * 2ème sous titre
     */
    private String sousTitre2;

    /**
     * 2ème paragraphe
     */
    private String paragraphe2;

    /**
     * photo 1
     */
    private String photo1;

    /**
     * photo 2
     */
    private String photo2;

    /**
     * photo 3
     */
    private String photo3;

}
