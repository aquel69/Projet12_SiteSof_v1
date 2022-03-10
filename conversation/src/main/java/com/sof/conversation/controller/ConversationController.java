package com.sof.conversation.controller;

import com.sof.conversation.dao.DaoConversation;
import com.sof.conversation.model.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConversationController {

    @Autowired
    DaoConversation daoConversation;

    /**
     * récupère une liste de toutes les conversations dans la base de données
     * @return List Conversation
     */
    @GetMapping(value = "/toutesLesConversations")
    public List<Conversation> findAllConversation() {
        List<Conversation> conversations = daoConversation.findAll();

        return conversations;
    }

    /**
     * récupère une liste de toutes les conversations selon l'id du membre dans la base de données
     * @param idMembre idMembre
     * @return List Conversation
     */
    @GetMapping(value = "/conversationsSelonMembre/{idMembre}")
    public List<Conversation> conversationsSelonMembre(@PathVariable int idMembre) {
        List<Conversation> conversations = daoConversation.conversationsSelonMembre(idMembre);

        return conversations;
    }

    /**
     * récupère une liste des conversations selon la date d'ajout dans la base de données
     * @return List Conversation
     */
    @GetMapping(value = "/conversationsSelonMembre")
    public List<Conversation> conversationSelonDateAjoutPourListeMembre() {
        List<Conversation> conversations = daoConversation.conversationSelonDateAjoutPourListeMembre();

        return conversations;
    }

    /**
     * ajoute une conversation dans la base de données
     * @param conversation conversation
     * @return Conversation
     */
    @PostMapping(value = "/ajouterConversation")
    public Conversation saveConversation(@RequestBody Conversation conversation) {
        return daoConversation.save(conversation);
    }

    /**
     * supprime les conversations selon l'id de l'utilisateur dans la base de données
     * @param idUtilisateur idUtilisateur
     */
    @DeleteMapping(value="/supprimerConversationsUtilisateur/{idUtilisateur}")
    public void supprimerConversationsUtilisateur(@PathVariable int idUtilisateur) {
        daoConversation.supprimerConversationsUtilisateur(idUtilisateur);
    }
}
