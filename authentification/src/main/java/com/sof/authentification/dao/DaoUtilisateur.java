package com.sof.authentification.dao;

import com.sof.authentification.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoUtilisateur extends JpaRepository<Utilisateur, Integer>  {

        Utilisateur findByEmail(String email);

}
