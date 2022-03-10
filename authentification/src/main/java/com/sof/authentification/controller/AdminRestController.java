package com.sof.authentification.controller;

import com.sof.authentification.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AdminRestController {

    @Autowired
    AuthentificationController authentificationController;

    @GetMapping(path="/All")
    public List<Utilisateur> getAllUtilisateurs() {
        return authentificationController.findAllUtilisateur();
    }
}
