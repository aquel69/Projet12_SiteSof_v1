package com.sof.interface_donnees.dao;

import com.sof.interface_donnees.model.AlbumInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoAlbumInterface extends JpaRepository<AlbumInterface, Integer> {

    AlbumInterface findByIdAlbumInterface(int id);

}
