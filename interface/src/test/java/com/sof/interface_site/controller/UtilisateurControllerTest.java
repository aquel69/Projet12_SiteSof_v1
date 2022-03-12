package com.sof.interface_site.controller;

import com.sof.interface_site.model.Utilisateur;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
public class UtilisateurControllerTest {

    @InjectMocks
    private UtilisateurController utilisateurController;

    @Test
    @Tag("verificationCorrespondanceMotDePasse")
    @DisplayName("vérifie que les 2 mots de passe sont identiques")
    public void verificationCorrespondanceMotDePasseCorrect() {
        //-- Arrange
        String motDePasseA = "password";
        String motDePasseB = "password";

        //-- Act
        boolean verification = utilisateurController.verificationCorrespondanceMotDePasse(motDePasseA, motDePasseB);
        //-- Insert
        assertTrue(verification);
    }

    @Test
    @Tag("verificationCorrespondanceMotDePasse")
    @DisplayName("vérifie que les 2 mots de passe ne sont pas identiques")
    public void verificationCorrespondanceMotDePasseInCorrect() {
        //-- Arrange
        String motDePasseA = "password";
        String motDePasseB = "wrongPassword";

        //-- Act
        boolean verification = utilisateurController.verificationCorrespondanceMotDePasse(motDePasseA, motDePasseB);
        //-- Insert
        assertFalse(verification);
    }

    @Test
    @Tag("verificationUsernameDejaExistant")
    @DisplayName("vérifie que le username est déjà existant")
    public void verificationUserDejaExistantCorrect() {
        //-- Arrange
        List<Utilisateur> listUtilisateurs = new ArrayList<>();
        Utilisateur utilisateur1 = new Utilisateur();
        Utilisateur utilisateur2 = new Utilisateur();

        utilisateur1.setUsername("Username");
        utilisateur2.setUsername("Username");
        listUtilisateurs.add(utilisateur1);

        //-- Act
        boolean verification = utilisateurController.verificationUsernameDejaExistant(listUtilisateurs, utilisateur2);
        //-- Insert
        assertFalse(verification);
    }

    @Test
    @Tag("verificationUsernameDejaExistant")
    @DisplayName("vérifie que le username n'est pas existant")
    public void verificationUserDejaExistantIncorrect() {
        //-- Arrange
        List<Utilisateur> listUtilisateurs = new ArrayList<>();
        Utilisateur utilisateur1 = new Utilisateur();
        Utilisateur utilisateur2 = new Utilisateur();

        utilisateur1.setUsername("Username");
        utilisateur2.setUsername("OtherUsername");
        listUtilisateurs.add(utilisateur1);

        //-- Act
        boolean verification = utilisateurController.verificationUsernameDejaExistant(listUtilisateurs, utilisateur2);
        //-- Insert
        assertTrue(verification);
    }

}
