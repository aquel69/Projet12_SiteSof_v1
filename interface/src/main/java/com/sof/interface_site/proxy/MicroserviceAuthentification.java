package com.sof.interface_site.proxy;

import com.sof.interface_site.model.Adresse;
import com.sof.interface_site.model.Utilisateur;
import com.sof.interface_site.model.UtilisateurAuth;
import com.sof.interface_site.model.UtilisateurAuthentification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @GetMapping(value = "/adresseSelonId/{id}")
    Adresse adresseSelonId(@PathVariable int id);

    @GetMapping(value = "/derniereAdresse")
    int recupererDernierAdresse();

    @GetMapping(value = "/utilisateurSelonEmail/{email}")
    Utilisateur findUtilisateurByEmail(@PathVariable String email);

    @GetMapping(value = "/utilisateurSelonUsername/{username}")
    Utilisateur findUtilisateurByUsername(@PathVariable String username);

    @GetMapping(value = "/utilisateurAuthentificationSelonUsername/{username}")
    UtilisateurAuthentification findUtilisateurAuthentificationByUsername(@PathVariable String username);

    @GetMapping(value = "/utilisateurSelonId/{id}")
    UtilisateurAuthentification findUtilisateurById(@PathVariable int id);

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurAuthentification login(@Valid @RequestBody UtilisateurAuth utilisateurAuth);

    @GetMapping(value = "/tousLesUtilisateurs")
    List<Utilisateur> findAllUtilisateur();



}
