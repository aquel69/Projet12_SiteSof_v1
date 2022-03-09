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

    @GetMapping(value = "/toutesLesConversations")
    public List<Conversation> findAllConversation() {
        List<Conversation> conversations = daoConversation.findAll();

        return conversations;
    }

    @GetMapping(value = "/conversationsSelonMembre/{idMembre}")
    public List<Conversation> conversationsSelonMembre(@PathVariable int idMembre) {
        List<Conversation> conversations = daoConversation.conversationsSelonMembre(idMembre);

        return conversations;
    }

    @GetMapping(value = "/conversationsSelonMembre")
    public List<Conversation> conversationSelonDateAjoutPourListeMembre() {
        List<Conversation> conversations = daoConversation.conversationSelonDateAjoutPourListeMembre();

        return conversations;
    }

    @PostMapping(value = "/ajouterConversation")
    public Conversation saveConversation(@RequestBody Conversation conversation) {
        return daoConversation.save(conversation);
    }

    @DeleteMapping(value="/supprimerConversationsUtilisateur/{idUtilisateur}")
    public void supprimerConversationsUtilisateur(@PathVariable int idUtilisateur) {
        daoConversation.supprimerConversationsUtilisateur(idUtilisateur);
    }



}
