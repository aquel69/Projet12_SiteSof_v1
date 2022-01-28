package com.sof.newsletter_email.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Mail {

    private String from;
    /**
     * destinataire du mail
     */
    private String to;
    /**
     * objet du mail
     */
    private String subject;

    /**
     * message du mail
     */
    private String message;

    private String emetteur;

}
