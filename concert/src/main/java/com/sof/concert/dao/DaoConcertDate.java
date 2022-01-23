package com.sof.concert.dao;


import com.sof.concert.model.ConcertDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoConcertDate  extends JpaRepository<ConcertDate, Integer> {

    @Query(value = "SELECT * FROM concert_date ORDER BY \"date\" DESC", nativeQuery = true)
    List<ConcertDate> findAllUtilisateurByDate();

}
