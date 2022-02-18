package com.sof.newsletter_email.service;

import com.sof.newsletter_email.model.Mail;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    final Configuration configuration;
    final JavaMailSender javaMailSender;

    public EmailService(Configuration configuration, JavaMailSender javaMailSender) {
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Mail mail) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Email de l'utilisateur " + mail.getExpediteur());
        helper.setTo("alexandre.lardon@yahoo.fr");
        String emailContent = getEmailUserContent(mail);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    String getEmailUserContent(Mail mail) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("mail", mail);
        configuration.getTemplate("EmailUtilisateur.ftl").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }

    public void sendEmailNewletter(Mail mail) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(mail.getObjet());
        helper.setTo(mail.getDestinataire());
        String emailContent = getEmailNewsletterContent(mail);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    String getEmailNewsletterContent(Mail mail) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("mail", mail);
        configuration.getTemplate("EmailNewsletter.ftl").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }

    public void sendEmailBienvenue(Mail mail) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Votre compte a été créé sur le site de Sof");
        helper.setTo(mail.getUtilisateurAuthentification().getEmail());
        String emailContent = getEmailBienvenueContent(mail);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    String getEmailBienvenueContent(Mail mail) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("mail", mail);
        configuration.getTemplate("EmailBienvenue.ftl").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }

}
