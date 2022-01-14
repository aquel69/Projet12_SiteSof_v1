package com.sof.interface_site.proxie;

import com.sof.interface_site.model.Adresse;
import com.sof.interface_site.model.Utilisateur;
import com.sof.interface_site.model.UtilisateurAuthentification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-authentification", url = "localhost:9091")
public interface MicroserviceAuthentification {

    @PostMapping(value = "/AjouterUtilisateur/{id}")
    Utilisateur saveUtilisateur(@RequestBody Utilisateur utilisateur);

    @PostMapping(value = "/AjouterAdresse")
    Adresse addAdresse(@RequestBody Adresse adresse);

    @GetMapping(value = "/UtilisateurSelonEmail/{email}")
    Utilisateur findUtilisateurByEmail(@PathVariable String email);

    @PostMapping(value="/Login/{username}/{motDePasse}")
    UtilisateurAuthentification login(@PathVariable(value = "username") String username , @PathVariable(value = "motDePasse") String motDePasse);

}
