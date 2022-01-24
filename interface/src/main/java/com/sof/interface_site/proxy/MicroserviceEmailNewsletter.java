package com.sof.interface_site.proxy;

import com.sof.interface_site.model.EmailNewsletter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "microservice-newsletter-email", url = "localhost:9093")
public interface MicroserviceEmailNewsletter {

    @GetMapping(value = "/recupererTousLesEmailsNewsletter")
    List<EmailNewsletter> recupererTousLesEmailsNewsletter();

    @PutMapping(value="/ajouterEmailNewsletter")
    void modifierAbonne(@RequestBody EmailNewsletter emailNewsletter);

}
