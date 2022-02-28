package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhotoInterface {

    /**
     * id de la table photo_interface
     */
    private int idPhotoInterface;

    /**
     * photo de l'accueil
     */
    private String photoAccueil;

    /**
     * photo du paralax
     */
    private String photoParalax;

    /**
     * photo de la section album
     */
    private String photoAlbum;

    /**
     * photo de la section contact
     */
    private String photoContact;

    /**
     * photo de la page création du compte
     */
    private String photoCreationCompte;

    /**
     * photo de la page conversation membre
     */
    private String photoConversationMembre;

    /**
     * photo de la page modification du compte
     */
    private String photoModificationCompte;

    /**
     * photo de la page gestion du compte
     */
    private String photoGestionCompte;

}
