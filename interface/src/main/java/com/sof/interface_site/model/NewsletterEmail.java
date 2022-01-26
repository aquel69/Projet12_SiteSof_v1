package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsletterEmail {

    /**
     * email pour l'envoi des newsletters et id de la table email_newsletter
     */
    private String email;

}
