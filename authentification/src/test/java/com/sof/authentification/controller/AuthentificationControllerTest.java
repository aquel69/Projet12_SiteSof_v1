package com.sof.authentification.controller;

import com.sof.authentification.model.Adresse;
import com.sof.authentification.model.Utilisateur;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthentificationControllerTest {

    @Autowired
    AuthentificationController authentificationController;

    Utilisateur utilisateurAjoute;

    Utilisateur utilisateur;

    Adresse adresse;

    Adresse adresseAjoute;

    @BeforeAll
    public void initTest() {
        utilisateurAjoute = new Utilisateur();
        utilisateur = new Utilisateur();
        adresse = new Adresse();
        adresseAjoute = new Adresse();
    }

    @AfterAll
    public void afterTest(){
        utilisateurAjoute = null;
        utilisateur = null;
        adresse = null;
        adresseAjoute = null;
    }

    @Test
    @Order(1)
    @Tag("addAdresse")
    @DisplayName("vérifie que l'adresse ajouté à la base de données n'est pas null")
    public void ajoutDUneAdresseTest() {
        //-- Arrange
        adresse.setAdresse("36 quai des orfèvres");
        adresse.setComplementAdresse("Batiment 1");
        adresse.setCodePostal("75001");
        adresse.setVille("Paris");
        //-- Act
        adresseAjoute = authentificationController.addAdresse(adresse);
        //-- Insert
        assertThat(adresseAjoute).isNotNull();
    }

    @Test
    @Order(2)
    @Tag("adresseSelonId")
    @DisplayName("vérifie que l'adresse n'est pas null")
    public void verificationAdresseNEstPasNullTest() {
        //-- Arrange
        //-- Act
        adresse = authentificationController.adresseSelonId(adresseAjoute.getIdAdresse());
        //-- Insert
        assertThat(adresse).isNotNull();
    }

    @Test
    @Order(3)
    @Tag("saveUtilisateur")
    @DisplayName("vérifie que l'utilisateur ajouté à la base de données n'est pas null")
    public void ajoutDUnUtilisateurTest() {
        //-- Arrange
        utilisateur.setUsername("Aloux");
        utilisateur.setNom("Dupont");
        utilisateur.setPrenom("Gérard");
        utilisateur.setAdresseUtilisateur(adresseAjoute);
        utilisateur.setEmail("gerard.dupont@yahoo.fr");
        utilisateur.setMotDePasse("azeAZE123!");
        utilisateur.setDateAjout(LocalDateTime.now());

        //-- Act
        utilisateurAjoute = authentificationController.saveUtilisateur(utilisateur);
        //-- Insert
        assertThat(utilisateurAjoute).isNotNull();
    }

    @Test
    @Order(4)
    @Tag("saveRole")
    @DisplayName("vérifie que le role ajouté à la base de données n'est pas null")
    public void ajoutDUnRoleTest() {
        //-- Arrange
        utilisateur = null;
        //-- Act
        utilisateur = authentificationController.saveRole(utilisateurAjoute);
        //-- Insert
        assertThat(utilisateur).isNotNull();
    }

    @Test
    @Order(5)
    @Tag("supprimerRoleUtilisateur")
    @DisplayName("vérifie que le role à bien été supprimé")
    public void supressionDUnRoleTest() {
        //-- Arrange
        //-- Act
        boolean suppression = authentificationController.supprimerRoleUtilisateur(utilisateur.getIdUtilisateur());
        //-- Insert
        assertTrue(suppression);
    }

    @Test
    @Order(6)
    @Tag("supprimerUnUtilisateur")
    @DisplayName("vérifie que l'utilisateur à bien été supprimé")
    public void supressionDUnUtilisateurTest() {
        //-- Arrange
        //-- Act
        boolean suppression = authentificationController.supprimerUnUtilisateur(utilisateur.getIdUtilisateur());
        //-- Insert
        assertTrue(suppression);
    }

    @Test
    @Order(7)
    @Tag("supprimerUneAdresse")
    @DisplayName("vérifie que l'adresse à bien été supprimé")
    public void supressionDUneAdresseTest() {
        //-- Arrange
        //-- Act
        boolean suppression = authentificationController.supprimerUneAdresse(adresseAjoute.getIdAdresse());
        //-- Insert
        assertTrue(suppression);
    }

}
