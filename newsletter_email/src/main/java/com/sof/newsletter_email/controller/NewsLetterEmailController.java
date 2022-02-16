package com.sof.newsletter_email.controller;

import com.sof.newsletter_email.dao.DaoNewsletterEmail;
import com.sof.newsletter_email.dao.DaoUtilisateurAuthentification;
import com.sof.newsletter_email.model.Mail;
import com.sof.newsletter_email.model.NewsletterEmail;
import com.sof.newsletter_email.model.UtilisateurAuthentification;
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
    private DaoUtilisateurAuthentification daoUtilisateurAuthentification;

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

    @GetMapping(value = "/utilisateurSelonUsername/{username}")
    public UtilisateurAuthentification findUtilisateurByUsername(@PathVariable String username) {
        UtilisateurAuthentification utilisateur = daoUtilisateurAuthentification.findByUsername(username);

        return utilisateur;
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
        mail.setExpediteur(email);

        emailService.sendEmail(mail);
    }

    @PostMapping(value="/envoyerEmailBienvenue")
    public void envoyerEmailBienvenue(@RequestBody UtilisateurAuthentification utilisateurAuthentification) throws MessagingException, TemplateException, IOException {
        Mail mail = new Mail();

        mail.setUtilisateurAuthentification(utilisateurAuthentification);

        emailService.sendEmailBienvenue(mail);
    }

    @PostMapping(value="/envoyerEmailNewsletter")
    public void envoyerEmailNewsletter(@PathVariable String objet
            , @PathVariable String message, @PathVariable String emailDestinataire) throws MessagingException, TemplateException, IOException {
        Mail mail = new Mail();

        mail.setObjet(objet);
        mail.setMessage(message);
        mail.setDestinataire(emailDestinataire);

        emailService.sendEmailNewletter(mail);
    }
}