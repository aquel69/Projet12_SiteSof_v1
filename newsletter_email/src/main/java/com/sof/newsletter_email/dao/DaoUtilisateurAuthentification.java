package com.sof.newsletter_email.dao;

import com.sof.newsletter_email.model.UtilisateurAuthentification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoUtilisateurAuthentification extends JpaRepository<UtilisateurAuthentification, Integer> {

    UtilisateurAuthentification findByEmail(String email);

    UtilisateurAuthentification findByUsername(String username);

}
