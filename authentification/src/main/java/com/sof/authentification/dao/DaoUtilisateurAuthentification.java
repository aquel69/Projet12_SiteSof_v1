package com.sof.authentification.dao;

import com.sof.authentification.model.UtilisateurAuthentification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoUtilisateurAuthentification extends JpaRepository<UtilisateurAuthentification, Integer> {

    UtilisateurAuthentification findByEmail(String email);

}
