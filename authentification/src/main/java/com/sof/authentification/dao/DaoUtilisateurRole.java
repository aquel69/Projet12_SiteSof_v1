package com.sof.authentification.dao;

import com.sof.authentification.model.UtilisateurRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface DaoUtilisateurRole extends JpaRepository <UtilisateurRole, Integer> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="DELETE FROM utilisateur_role WHERE utilisateur_id = ?", nativeQuery = true)
    void supprimerRoleUtilisateur(int idMembre);
}
