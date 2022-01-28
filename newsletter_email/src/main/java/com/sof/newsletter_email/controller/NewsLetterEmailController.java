package com.sof.newsletter_email.controller;

import com.sof.newsletter_email.dao.DaoNewsletterEmail;
import com.sof.newsletter_email.model.Mail;
import com.sof.newsletter_email.model.NewsletterEmail;
import com.sof.newsletter_email.service.EmailService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
public class NewsLetterEmailController {

    @Autowired
    private DaoNewsletterEmail daoNewsletterEmail;

    @Autowired
    private Configuration freemarkerConfig;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    EmailService emailService;

    private String typeEmail;

    @GetMapping(value = "/recupererTousLesEmailsNewsletter")
    public List<NewsletterEmail> recupererTousLesNewslettersEmail() {
        List<NewsletterEmail> listeEmailNewsletters = daoNewsletterEmail.findAll();

        return listeEmailNewsletters;
    }


    @PostMapping(value="/ajouterEmailNewsletter")
    public void ajouterEmailNewsletter(@RequestBody NewsletterEmail newsletterEmail) {
        daoNewsletterEmail.save(newsletterEmail);
    }

    @PostMapping(value="/envoyerEmailUtilisateurAAdmin/{nom}/{email}/{message}")
    public void envoyerEmailUtilisateurAAdmin(@PathVariable String nom, @PathVariable String email
            , @PathVariable String message) throws MessagingException, TemplateException, IOException {
        Mail mail = new Mail();

        mail.setEmetteur(nom);
        mail.setMessage(message);
        mail.setFrom(email);

        emailService.sendEmail(mail);
    }
}