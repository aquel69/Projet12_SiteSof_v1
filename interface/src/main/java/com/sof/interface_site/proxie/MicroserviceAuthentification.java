package com.sof.interface_site.proxie;

import com.sof.interface_site.model.Adresse;
import com.sof.interface_site.model.Utilisateur;
import com.sof.interface_site.model.UtilisateurAuth;
import com.sof.interface_site.model.UtilisateurAuthentification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "microservice-authentification", url = "localhost:9091")
public interface MicroserviceAuthentification {

    @PostMapping(value = "/AjouterUtilisateur/{id}")
    Utilisateur saveUtilisateur(@RequestBody Utilisateur utilisateur);

    @PostMapping(value = "/AjouterAdresse")
    Adresse addAdresse(@RequestBody Adresse adresse);

    @GetMapping(value = "/UtilisateurSelonEmail/{email}")
    Utilisateur findUtilisateurByEmail(@PathVariable String email);

    @PostMapping(value="/Login")
    Utilisateur login(@Valid @RequestBody UtilisateurAuth utilisateur);

}
