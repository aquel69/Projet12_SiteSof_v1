package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String objet;

    /**
     * message du mail
     */
    private String message;


    private UtilisateurAuthentification utilisateurAuthentification;

}
