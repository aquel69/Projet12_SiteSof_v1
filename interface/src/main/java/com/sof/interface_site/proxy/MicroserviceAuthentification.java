package com.sof.interface_site.proxy;

import com.sof.interface_site.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "microservice-authentification", url = "localhost:9091")
public interface MicroserviceAuthentification {

    @PostMapping(value = "/ajouterUtilisateur")
    Utilisateur saveUtilisateur(@RequestBody Utilisateur utilisateur);

    @PostMapping(value = "/ajouterAdresse")
    Adresse addAdresse(@RequestBody Adresse adresse);

    @PostMapping(value = "/ajouterRole")
    Utilisateur saveRole(@RequestBody Utilisateur utilisateur);

    @GetMapping(value = "/derniereAdresse")
    int recupererDernierAdresse();

    @GetMapping(value = "/utilisateurSelonEmail/{email}")
    Utilisateur findUtilisateurByEmail(@PathVariable String email);

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurAuthentification login(@Valid @RequestBody UtilisateurAuth utilisateurAuth);

    @GetMapping(value = "/tousLesUtilisateurs")
    List<Utilisateur> findAllUtilisateur();



}
