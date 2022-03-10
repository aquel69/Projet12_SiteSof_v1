package com.sof.newsletter_email.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Mail {

    /**
     * expediteur
     */
    private String expediteur;

    /**
     * destinataire du mail
     */
    private String destinataire;
    /**
     * objet du mail
     */
    private String objet;

    /**
     * message du mail
     */
    private String message;

    /**
     * émetteur
     */
    private String emetteur;

    /**
     * utilisateur authentifié
     */
    private UtilisateurAuthentification utilisateurAuthentification;

}
