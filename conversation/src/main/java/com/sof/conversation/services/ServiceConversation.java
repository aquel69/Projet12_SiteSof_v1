package com.sof.conversation.services;

import com.sof.conversation.dao.DaoConversation;
import com.sof.conversation.dao.DaoUtilisateurAuthentification;
import com.sof.conversation.model.Conversation;
import com.sof.conversation.model.ConversationBDD;
import com.sof.conversation.model.UtilisateurAuthentification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceConversation {

    @Autowired
    DaoConversation daoConversation;

    @Autowired
    DaoUtilisateurAuthentification daoUtilisateurAuthentification;

    public Conversation conversationAvecUtilisateur (ConversationBDD pConversation) {

        Conversation conversation = new Conversation();
        UtilisateurAuthentification membre = daoUtilisateurAuthentification.findById(pConversation.getMembre());
        UtilisateurAuthentification interlocuteur = daoUtilisateurAuthentification.findById(pConversation
                .getInterlocuteur());

        conversation.setIdCommentaire(pConversation.getIdCommentaire());
        conversation.setDateAjout(pConversation.getDateAjout());
        conversation.setInterlocuteur(interlocuteur);
        conversation.setMembre(membre);
        conversation.setMessage(pConversation.getMessage());

        return conversation;
    }
}
