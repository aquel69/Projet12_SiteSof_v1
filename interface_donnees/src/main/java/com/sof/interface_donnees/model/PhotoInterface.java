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
@Table(name=("photo_interface"))
public class PhotoInterface {

    /**
     * id de la table photo_interface
     */
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="adresse_id_seq_1", initialValue = 1, allocationSize = 1)
    @Column(name="id_photo_interface")
    private int idPhotoInterface;

    /**
     * photo de l'accueil
     */
    @NonNull
    @Column(name="photo_accueil")
    private String photoAccueil;

    /**
     * photo du paralax
     */
    @NonNull
    @Column(name="photo_paralax")
    private String photoParalax;

    /**
     * photo de la section album
     */
    @NonNull
    @Column(name="photo_album")
    private String photoAlbum;

    /**
     * photo de la section contact
     */
    @NonNull
    @Column(name="photo_contact")
    private String photoContact;

    /**
     * photo de la page création du compte
     */
    @NonNull
    @Column(name="photo_creation_compte")
    private String photoCreationCompte;

    /**
     * photo de la page conversation membre
     */
    @NonNull
    @Column(name="photo_conversation_membre")
    private String photoConversationMembre;

    /**
     * photo de la page modification du compte
     */
    @NonNull
    @Column(name="photo_modification_compte")
    private String photoModificationCompte;

    /**
     * photo de la page gestion du compte
     */
    @NonNull
    @Column(name="photo_gestion_compte")
    private String photoGestionCompte;

}
