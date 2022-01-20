package com.sof.interface_site.proxie;

import com.sof.interface_site.model.Adresse;
import com.sof.interface_site.model.Utilisateur;
import com.sof.interface_site.model.UtilisateurAuth;
import com.sof.interface_site.model.UtilisateurAuthentification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "microservice-authentification", url = "localhost:9091")
public interface MicroserviceAuthentification {

    @PostMapping(value = "/AjouterUtilisateur/{id}")
    Utilisateur saveUtilisateur(@RequestBody Utilisateur utilisateur);

    @PostMapping(value = "/AjouterAdresse")
    Adresse addAdresse(@RequestBody Adresse adresse);

    @GetMapping(value = "/UtilisateurSelonEmail/{email}")
    Utilisateur findUtilisateurByEmail(@PathVariable String email);

    @PostMapping(value = "/Login", consumes = MediaType.APPLICATION_JSON_VALUE)
    Utilisateur login(@Valid @RequestBody UtilisateurAuth utilisateurAuth);

}
