package com.sof.interface_donnees.dao;

import com.sof.interface_donnees.model.ConcertInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoConcertInterface extends JpaRepository<ConcertInterface, Integer> {

    ConcertInterface findByIdConcertInterface(int id);

}
