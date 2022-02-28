package com.sof.authentification.dao;


import com.sof.authentification.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoRole extends JpaRepository<Role, Integer> {

    Role findByStatut(String statut);

    @Query(value = "SELECT statut FROM role\n" +
            "INNER JOIN utilisateur_role ur on role.id_role = ur.role_id\n" +
            "INNER JOIN utilisateur u on u.id_utilisateur = ur.utilisateur_id\n" +
            "WHERE u.id_utilisateur = ?", nativeQuery = true)
    List<String> listeDesRolesDeLUtilisateur(int idUtilisateur);

}
