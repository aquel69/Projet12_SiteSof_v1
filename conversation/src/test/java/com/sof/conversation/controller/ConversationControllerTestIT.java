package com.sof.conversation.controller;

import com.sof.conversation.model.Conversation;
import com.sof.conversation.model.ConversationBDD;
import com.sof.conversation.model.Role;
import com.sof.conversation.model.UtilisateurAuthentification;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConversationControllerTestIT {

    @Autowired
    ConversationController conversationController;

    ConversationBDD conversation;

    ConversationBDD conversationAjoute;

    UtilisateurAuthentification utilisateurAuthentification;

    @BeforeAll
    public void initTest() {
        conversation = new ConversationBDD();
        conversationAjoute = new ConversationBDD();
        utilisateurAuthentification = new UtilisateurAuthentification();
    }

    @AfterAll
    public void afterTest(){
        conversation = null;
        conversationAjoute = null;
        utilisateurAuthentification = null;
    }

    @Test
    @Order(1)
    @Tag("saveConversation")
    @DisplayName("vérifie que la conversation a bien été ajouté dans la base de données")
    public void ajoutDUneConversationTest() {
        //-- Arrange
        List<Role> roles = new ArrayList<>();
        Role role = new Role();

        role.setIdRole(2);
        role.setStatut("ROLE_MEMBER");

        roles.add(role);

        utilisateurAuthentification.setIdUtilisateur(1);
        utilisateurAuthentification.setEmail("gerard.dupont@yahoo.fr");
        utilisateurAuthentification.setRoles(roles);
        utilisateurAuthentification.setUsername("Waz");

        conversation.setInterlocuteur(3);
        conversation.setMembre(3);
        conversation.setDateAjout(LocalDateTime.now());
        conversation.setMessage("Bonjour");
        //-- Act
        conversationAjoute = conversationController.saveConversation(conversation);
        //-- Insert
        assertThat(conversationAjoute.getMessage()).isEqualTo(conversation.getMessage());
    }

    @Test
    @Order(2)
    @Tag("supprimerConversationsUtilisateur")
    @DisplayName("vérifie que la conversation a bien été supprimé de la base de données")
    public void supressionDUneConversationTest() {
        //-- Arrange
        //-- Act
        boolean verification = conversationController.supprimerConversationsUtilisateur
                (utilisateurAuthentification.getIdUtilisateur());
        //-- Insert
        assertTrue(verification);
    }

}
