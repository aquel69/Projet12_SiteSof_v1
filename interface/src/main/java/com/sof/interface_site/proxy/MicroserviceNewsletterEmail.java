package com.sof.interface_site.proxy;

import com.sof.interface_site.model.NewsletterEmail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "microservice-newsletter-email", url = "localhost:9093")
public interface MicroserviceNewsletterEmail {

    @GetMapping(value = "/recupererTousLesEmailsNewsletter")
    List<NewsletterEmail> recupererTousLesEmailsNewsletter();

    @PostMapping(value="/ajouterEmailNewsletter")
    void ajouterEmailNewsletter(@RequestBody NewsletterEmail newsletterEmail);

}
