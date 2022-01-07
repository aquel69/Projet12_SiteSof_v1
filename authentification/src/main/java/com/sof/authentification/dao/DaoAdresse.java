package com.sof.authentification.dao;

import com.sof.authentification.model.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoAdresse extends JpaRepository<Adresse, Integer> {

        Adresse findByIdAdresse(int id);

        @Query(value = "SELECT MAX(id_adresse) FROM adresse", nativeQuery = true)
        Integer recupererDernierAdresse();

}
