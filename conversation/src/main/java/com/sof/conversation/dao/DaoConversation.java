package com.sof.conversation.dao;

import com.sof.conversation.model.Conversation;
import com.sof.conversation.model.ConversationBDD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DaoConversation extends JpaRepository<ConversationBDD, Integer> {

    @Query(value="SELECT * FROM conversation  WHERE membre_id = ? ORDER BY date_ajout DESC", nativeQuery = true)
    List<ConversationBDD> conversationsSelonMembre(int idMembre);


    @Query(value="SELECT * FROM (SELECT DISTINCT ON (membre_id) * FROM conversation WHERE conversation.interlocuteur_id" +
            " NOT IN (3) ORDER BY membre_id, date_ajout DESC) t ORDER BY date_ajout DESC;", nativeQuery = true)
    List<ConversationBDD> conversationSelonDateAjoutPourListeMembre();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="DELETE FROM conversation WHERE membre_id = ?", nativeQuery = true)
    void supprimerConversationsUtilisateur(int idMembre);


}
