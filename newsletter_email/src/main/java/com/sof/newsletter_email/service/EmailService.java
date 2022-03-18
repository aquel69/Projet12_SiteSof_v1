package com.sof.newsletter_email.service;

import com.sof.newsletter_email.model.Mail;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class EmailService {

    final Configuration configuration;
    final JavaMailSender javaMailSender;

    /**
     * constructeur de l'EmailService
     * @param configuration configuration
     * @param javaMailSender javaMailSender
     */
    public EmailService(Configuration configuration, JavaMailSender javaMailSender) {
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
    }

    /**
     * permet d'envoyer un email de l'utilisateur à l'administrateur
     * @param mail mail
     * @throws MessagingException MessagingException
     * @throws IOException IOException
     * @throws TemplateException TemplateException
     */
    public void sendEmail(Mail mail) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Email de l'utilisateur " + mail.getExpediteur());
        helper.setTo("alexandre.lardon@yahoo.fr");
        String emailContent = getEmailUserContent(mail);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    /**
     * contenu de l'email de l'utilisateur à l'administrateur
     * @param mail mail
     * @return
     * @throws IOException IOException
     * @throws TemplateException TemplateException
     */
    String getEmailUserContent(Mail mail) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("mail", mail);
        configuration.getTemplate("EmailUtilisateur.ftl").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }

    /**
     * permet d'envoyer un email Newsletter à un utilisateur
     * @param mail mail
     * @throws MessagingException MessagingException
     * @throws IOException IOException
     * @throws TemplateException TemplateException
     */
    public void sendEmailNewletter(Mail mail) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(mail.getObjet());
        helper.setTo(mail.getDestinataire());
        String emailContent = getEmailNewsletterContent(mail);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    /**
     * contenu de l'email Newsletter à un utilisateur
     * @param mail mail
     * @return
     * @throws IOException IOException
     * @throws TemplateException TemplateException
     */
    String getEmailNewsletterContent(Mail mail) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("mail", mail);
        configuration.getTemplate("EmailNewsletter.ftl").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }

    /**
     * permet d'envoyer un email de bienvenue à un utilisateur
     * @param mail mail
     * @throws MessagingException MessagingException
     * @throws IOException  IOException
     * @throws TemplateException TemplateException
     */
    public void sendEmailBienvenue(Mail mail) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Votre compte a été créé sur le site de Sof");
        helper.setTo(mail.getUtilisateurAuthentification().getEmail());
        String emailContent = getEmailBienvenueContent(mail);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    /**
     * contenu de l'email de bienvenue à un utilisateur
     * @param mail mail
     * @return
     * @throws IOException IOException
     * @throws TemplateException TemplateException
     */
    String getEmailBienvenueContent(Mail mail) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("mail", mail);
        configuration.getTemplate("EmailBienvenue.ftl").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }

    /**
     * permet d'envoyer un email pour prévenir d'un nouveau message à un utilisateur
     * @param mail mail
     * @param administrateur  administrateur
     * @throws MessagingException MessagingException
     * @throws IOException IOException
     * @throws TemplateException TemplateException
     */
    public void sendEmailConversation(Mail mail, boolean administrateur) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(mail.getObjet());
        if (administrateur){
            helper.setTo(mail.getUtilisateurAuthentification().getEmail());
        } else {
            helper.setTo("serveursof@gmail.com");
        }
        String emailContent = getEmailConversationContent(mail, administrateur);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    /**
     * contenu de l'email pour prévenir d'un nouveau message à un utilisateur
     * @param mail mail
     * @return StringWriter
     * @throws IOException IOException
     * @throws TemplateException TemplateException
     */
    String getEmailConversationContent(Mail mail, boolean administrateur) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("mail", mail);
        if (administrateur){
            configuration.getTemplate("EmailConversationAdmin.ftl").process(model, stringWriter);
        } else {
            configuration.getTemplate("EmailConversation.ftl").process(model, stringWriter);
        }

        return stringWriter.getBuffer().toString();
    }
}
