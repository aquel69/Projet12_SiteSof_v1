package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class Mail {

    /**
     * adresse mail de l'expediteur
     */
    private String expediteur;

    /**
     * nom de l'emetteur
     */
    private String emetteur;

    /**
     * mail du destinataire
     */
    private String destinataire;
    /**
     * objet du mail
     */
    @Size(min=2, max = 256, message = "L''objet doit être compris entre 2 et 256 caractères")
    private String objet;

    /**
     * message du mail
     */
    @Size(min=5, message = "L''objet doit contenir au minimum 5 caractères")
    private String message;


    private UtilisateurAuthentification utilisateurAuthentification;

}
