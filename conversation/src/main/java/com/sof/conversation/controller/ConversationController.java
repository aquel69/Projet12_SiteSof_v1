package com.sof.conversation.controller;

import com.sof.conversation.dao.DaoConversation;
import com.sof.conversation.dao.DaoUtilisateurAuthentification;
import com.sof.conversation.model.Conversation;
import com.sof.conversation.model.ConversationBDD;
import com.sof.conversation.model.UtilisateurAuthentification;
import com.sof.conversation.services.ServiceConversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ConversationController {

    @Autowired
    DaoConversation daoConversation;

    @Autowired
    DaoUtilisateurAuthentification daoUtilisateurAuthentification;

    @Autowired
    ServiceConversation serviceConversation;

    /**
     * récupère une liste de toutes les conversations dans la base de données
     * @return List Conversation
     */
    @GetMapping(value = "/toutesLesConversations")
    public List<Conversation> findAllConversation() {
        List<ConversationBDD> conversationsBDD = daoConversation.findAll();
        List<Conversation> conversations = new ArrayList<>();

        for (ConversationBDD conversationBDD : conversationsBDD) {
            Conversation conversation = serviceConversation.conversationAvecUtilisateur(conversationBDD);
            conversations.add(conversation);
        }

        return conversations;
    }

    /**
     * récupère une liste de toutes les conversations selon l'id du membre dans la base de données
     * @param idMembre idMembre
     * @return List Conversation
     */
    @GetMapping(value = "/conversationsSelonMembre/{idMembre}")
    public List<Conversation> conversationsSelonMembre(@PathVariable int idMembre) {
        List<ConversationBDD> conversationsBDD = daoConversation.conversationsSelonMembre(idMembre);
        List<Conversation> conversations = new ArrayList<>();

        for (ConversationBDD conversationBDD : conversationsBDD) {
            Conversation conversation = serviceConversation.conversationAvecUtilisateur(conversationBDD);
            conversations.add(conversation);
        }

        return conversations;
    }

    /**
     * récupère une liste des conversations selon la date d'ajout dans la base de données
     * @return List Conversation
     */
    @GetMapping(value = "/conversationsSelonMembre")
    public List<Conversation> conversationSelonDateAjoutPourListeMembre() {
        List<ConversationBDD> conversationsBDD = daoConversation.conversationSelonDateAjoutPourListeMembre();
        List<Conversation> conversations = new ArrayList<>();

        for (ConversationBDD conversationBDD : conversationsBDD) {
            Conversation conversation = serviceConversation.conversationAvecUtilisateur(conversationBDD);
            conversations.add(conversation);
        }

        return conversations;
    }

    /**
     * ajoute une conversation dans la base de données
     * @param conversation conversation
     * @return Conversation
     */
    @PostMapping(value = "/ajouterConversation")
    public ConversationBDD saveConversation(@RequestBody ConversationBDD conversation) {
        return daoConversation.save(conversation);
    }

    /**
     * supprime les conversations selon l'id de l'utilisateur dans la base de données
     * @param idUtilisateur idUtilisateur
     * @return boolean
     */
    @DeleteMapping(value="/supprimerConversationsUtilisateur/{idUtilisateur}")
    public boolean supprimerConversationsUtilisateur(@PathVariable int idUtilisateur) {
        daoConversation.supprimerConversationsUtilisateur(idUtilisateur);
        return true;
    }

    /**
     * récupère un utilisateurAuthentification selon son id dans la base de données
     * @param id id
     * @return UtilisateurAuthentification
     */
    @GetMapping(value = "/utilisateurSelonId/{id}")
    public UtilisateurAuthentification findUtilisateurById(@PathVariable int id) {
        UtilisateurAuthentification utilisateurAuthentification = daoUtilisateurAuthentification.findById(id);

        return utilisateurAuthentification;
    }
}
