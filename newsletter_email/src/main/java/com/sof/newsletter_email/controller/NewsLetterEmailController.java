package com.sof.newsletter_email.controller;

import com.sof.newsletter_email.dao.DaoNewsletterEmail;
import com.sof.newsletter_email.dao.DaoUtilisateurAuthentification;
import com.sof.newsletter_email.model.Mail;
import com.sof.newsletter_email.model.NewsletterEmail;
import com.sof.newsletter_email.model.UtilisateurAuthentification;
import com.sof.newsletter_email.service.EmailService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
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
    EmailService emailService;

    /**
     * récupère tous les emails newsletter dans la base de données
     *
     * @return List NewsletterEmail
     */
    @GetMapping(value = "/recupererTousLesEmailsNewsletter")
    public List<NewsletterEmail> recupererTousLesNewslettersEmail() {
        List<NewsletterEmail> listeEmailNewsletters = daoNewsletterEmail.findAll();

        return listeEmailNewsletters;
    }

    /**
     * récupère un utilisateur selon son username dans la base de données
     *
     * @param username username
     * @return UtilisateurAuthentification
     */
    @GetMapping(value = "/utilisateurSelonUsername/{username}")
    public UtilisateurAuthentification findUtilisateurByUsername(@PathVariable String username) {
        UtilisateurAuthentification utilisateur = daoUtilisateurAuthentification.findByUsername(username);

        return utilisateur;
    }

    /**
     * ajoute un email newsletter dans la base de données
     * @param newsletterEmail newsletterEmail
     * @return NewsletterEmail
     */
    @PostMapping(value = "/ajouterEmailNewsletter")
    public NewsletterEmail ajouterEmailNewsletter(@RequestBody NewsletterEmail newsletterEmail) {
        NewsletterEmail newsletterEmail1;
        newsletterEmail1 = daoNewsletterEmail.save(newsletterEmail);
        return newsletterEmail1;
    }

    /**
     * supprimer un emailNewsletter de la base de données
     * @param email email
     * @return boolean
     */
    @DeleteMapping(value="/desinscrireMembreNewsletter/{email}")
    public boolean supprimerEmailNewsletter(@PathVariable String email) {
        daoNewsletterEmail.supprimerEmailNewsletter(email);

        return true;
    }

    /**
     * envoi un email utilisateur à l'administrateur
     *
     * @param nom     nom
     * @param email   email
     * @param message message
     * @throws MessagingException MessagingException
     * @throws TemplateException  TemplateException
     * @throws IOException        IOException
     */
    @PostMapping(value = "/envoyerEmailUtilisateurAAdmin/{nom}/{email}/{message}")
    public void envoyerEmailUtilisateurAAdmin(@PathVariable String nom, @PathVariable String email
            , @PathVariable String message) throws MessagingException, TemplateException, IOException {
        Mail mail = new Mail();

        mail.setEmetteur(nom);
        mail.setMessage(message);
        mail.setExpediteur(email);

        emailService.sendEmail(mail);
    }

    /**
     * envoi un email de bienvenue lors de la création du compte
     *
     * @param utilisateurAuthentification utilisateurAuthentification
     * @throws MessagingException MessagingException
     * @throws TemplateException  TemplateException
     * @throws IOException        IOException
     */
    @PostMapping(value = "/envoyerEmailBienvenue")
    public void envoyerEmailBienvenue(@RequestBody UtilisateurAuthentification utilisateurAuthentification) throws MessagingException, TemplateException, IOException {
        Mail mail = new Mail();

        mail.setUtilisateurAuthentification(utilisateurAuthentification);

        emailService.sendEmailBienvenue(mail);
    }

    /**
     * envoi un emailNewsletter à un destinataire
     *
     * @param mail mail
     * @throws MessagingException MessagingException
     * @throws TemplateException  TemplateException
     * @throws IOException        IOException
     */
    @PostMapping(value = "/envoyerEmailNewsletter")
    public void envoyerEmailNewsletter(@RequestBody Mail mail)
            throws MessagingException, TemplateException, IOException {
        Mail mailAEnvoyer = new Mail();

        mailAEnvoyer.setObjet(mail.getObjet());
        mailAEnvoyer.setMessage(mail.getMessage());
        mailAEnvoyer.setDestinataire(mail.getDestinataire());

        emailService.sendEmailNewletter(mailAEnvoyer);
    }

    /**
     * envoi un email à un destinataire pour le prévenir d'un nouveau message de conversation
     * @param utilisateurAuthentification utilisateurAuthentification
     * @param objet objet
     * @param administrateur administrateur
     * @throws MessagingException MessagingException
     * @throws TemplateException TemplateException
     * @throws IOException IOException
     */
    @PostMapping(value = "/envoyerEmailConversation/{objet}/{administrateur}")
    public void envoyerEmailConversation(@RequestBody UtilisateurAuthentification utilisateurAuthentification
            , @PathVariable String objet, @PathVariable boolean administrateur)
            throws MessagingException, TemplateException, IOException {
        Mail mailAEnvoyer = new Mail();

        mailAEnvoyer.setObjet(objet);
        mailAEnvoyer.setUtilisateurAuthentification(utilisateurAuthentification);

        emailService.sendEmailConversation(mailAEnvoyer, administrateur);
    }
}