package com.sof.interface_donnees.dao;

import com.sof.interface_donnees.model.BiographieInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoBiographieInterface extends JpaRepository<BiographieInterface,Integer> {

    BiographieInterface findByIdBiographieInterface(int id);

}
