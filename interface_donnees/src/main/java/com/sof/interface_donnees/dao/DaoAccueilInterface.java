package com.sof.interface_donnees.dao;

import com.sof.interface_donnees.model.AccueilInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoAccueilInterface extends JpaRepository<AccueilInterface, String> {

        @Query(value = "SELECT * FROM accueil_interface WHERE video_youtube = 'https://www.youtube.com/embed/yRWCAFs1oY8?controls=0'", nativeQuery = true)
        String getUrlVideoYoutube();


}
