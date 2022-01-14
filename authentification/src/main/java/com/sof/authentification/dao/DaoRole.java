package com.sof.authentification.dao;


import com.sof.authentification.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoRole extends JpaRepository<Role, Integer> {

    Role findByStatut(String statut);

}
