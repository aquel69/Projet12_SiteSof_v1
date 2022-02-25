package com.sof.conversation.dao;

import com.sof.conversation.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoConversation extends JpaRepository<Conversation, Integer> {

    @Query(value="SELECT * FROM conversation  WHERE membre_id = ? ORDER BY date_ajout DESC", nativeQuery = true)
    List<Conversation> conversationsSelonMembre(int idMembre);


    @Query(value="SELECT * FROM (SELECT DISTINCT ON (membre_id) * FROM conversation " +
            "ORDER BY membre_id, date_ajout DESC) t ORDER BY date_ajout DESC", nativeQuery = true)
    List<Conversation> conversationSelonDateAjoutPourListeMembre();


}
