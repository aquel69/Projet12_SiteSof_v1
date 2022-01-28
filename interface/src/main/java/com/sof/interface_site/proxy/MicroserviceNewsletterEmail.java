package com.sof.interface_site.proxy;

import com.sof.interface_site.model.NewsletterEmail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@FeignClient(name = "microservice-newsletter-email", url = "localhost:9093")
public interface MicroserviceNewsletterEmail {

    @GetMapping(value = "/recupererTousLesEmailsNewsletter")
    List<NewsletterEmail> recupererTousLesEmailsNewsletter();

    @PostMapping(value="/ajouterEmailNewsletter")
    void ajouterEmailNewsletter(@RequestBody NewsletterEmail newsletterEmail);

    @PostMapping(value="/envoyerEmailUtilisateurAAdmin/{nom}/{email}/{message}")
    void envoyerEmailUtilisateurAAdmin(@PathVariable String nom, @PathVariable String email
            , @PathVariable String message);

}
