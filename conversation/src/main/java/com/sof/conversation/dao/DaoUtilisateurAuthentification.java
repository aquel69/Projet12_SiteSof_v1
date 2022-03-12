package com.sof.conversation.dao;

import com.sof.conversation.model.UtilisateurAuthentification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoUtilisateurAuthentification extends JpaRepository<UtilisateurAuthentification, Integer> {

    UtilisateurAuthentification findById(int id);

}
