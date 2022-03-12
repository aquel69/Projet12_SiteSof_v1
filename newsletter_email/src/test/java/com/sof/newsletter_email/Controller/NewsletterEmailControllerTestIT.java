package com.sof.newsletter_email.Controller;

import com.sof.newsletter_email.controller.NewsLetterEmailController;
import com.sof.newsletter_email.model.NewsletterEmail;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewsletterEmailControllerTestIT {

    @Autowired
    NewsLetterEmailController newsLetterEmailController;

    NewsletterEmail newsletterEmail;

    NewsletterEmail newsletterEmailAjoute;

    @BeforeAll
    public void initTest() {
        newsletterEmail = new NewsletterEmail();
    }

    @AfterAll
    public void afterTest(){
        newsletterEmail = null;
    }

    @Test
    @Order(1)
    @Tag("ajouterEmailNewsletter")
    @DisplayName("vérifie que l'emailNewsletter a bien été ajouté dans la base de données")
    public void ajouterEmailNewsletter() {
        //-- Arrange
        newsletterEmail.setEmail("gerard.dupont@yahoo.fr");
        //-- Act
        newsletterEmailAjoute = newsLetterEmailController.ajouterEmailNewsletter(newsletterEmail);
        //-- Insert
        assertThat(newsletterEmailAjoute.getEmail()).isEqualTo(newsletterEmail.getEmail());
    }

    @Test
    @Order(2)
    @Tag("supprimerEmailNewsletter")
    @DisplayName("vérifie que l'emailNewsletter à bien été supprimé de la base de données")
    public void supprimerEmailNewsletter() {
        //-- Arrange
        //-- Act
        boolean verification = newsLetterEmailController.supprimerEmailNewsletter(newsletterEmailAjoute.getEmail());
        //-- Insert
        assertTrue(verification);
    }
}
