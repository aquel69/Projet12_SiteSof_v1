package com.sof.newsletter_email.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Mail {

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

    private String emetteur;

    private UtilisateurAuthentification utilisateurAuthentification;

}
