package com.sof.newsletter_email.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name=("email_newsletter"))
public class NewsletterEmail {

    /**
     * email pour l'envoi des newsletters et id de la table email_newsletter
     */
    @Id
    @Column(name="email")
    private String email;
}
