package com.sof.newsletter_email.controller;

import com.sof.newsletter_email.dao.DaoNewsletterEmail;
import com.sof.newsletter_email.model.NewsletterEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsLetterEmailController {

    @Autowired
    DaoNewsletterEmail daoNewsletterEmail;

    @GetMapping(value = "/recupererTousLesEmailsNewsletter")
    public List<NewsletterEmail> recupererTousLesNewslettersEmail() {
        List<NewsletterEmail> listeEmailNewsletters = daoNewsletterEmail.findAll();

        return listeEmailNewsletters;
    }


    @PostMapping(value="/ajouterEmailNewsletter")
    public void ajouterEmailNewsletter(@RequestBody NewsletterEmail newsletterEmail) {
        daoNewsletterEmail.save(newsletterEmail);
    }

}
