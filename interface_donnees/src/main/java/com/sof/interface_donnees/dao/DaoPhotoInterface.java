package com.sof.interface_donnees.dao;

import com.sof.interface_donnees.model.PhotoInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoPhotoInterface extends JpaRepository<PhotoInterface, Integer> {


    PhotoInterface findByIdPhotoInterface(int id);

}
