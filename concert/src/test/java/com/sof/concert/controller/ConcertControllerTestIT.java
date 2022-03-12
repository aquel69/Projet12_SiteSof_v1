package com.sof.concert.controller;

import com.sof.concert.model.ConcertDate;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConcertControllerTestIT {

    @Autowired
    ConcertController concertController;

    ConcertDate concertDate;

    ConcertDate concertDateAjoute;

    @BeforeAll
    public void initTest() {
        concertDate = new ConcertDate();
        concertDateAjoute = new ConcertDate();
    }

    @AfterAll
    public void afterTest(){
        concertDate = null;
        concertDateAjoute = null;
    }

    @Test
    @Order(1)
    @Tag("ajouterUnConcert")
    @DisplayName("vérifie qu'un concert a été ajouté à la base de données")
    public void ajouterUnConcert() {
        //-- Arrange
        ConcertDate resultatConcert = null;
        int nbDeConcertAjoute = 0;
        concertDate.setDate(LocalDateTime.now());
        concertDate.setNomLieu("Ninkasi");
        concertDate.setAdresse("11 Chemin de la rose");
        concertDate.setTarif("Gratuit");
        //-- Act
        concertDateAjoute = concertController.ajouterUnConcert(concertDate);
        //-- Insert
        assertThat(concertDate).isNotNull();
    }

    @Test
    @Order(2)
    @Tag("findAllUtilisateur")
    @DisplayName("vérifie que la liste des concerts dans la base de données n'est pas égal à 0")
    public void verificationNbDeCaractereDuMessageIncorrect() {
        //-- Arrange
        List<ConcertDate> listeDeTousLesConcerts;
        //-- Act
        listeDeTousLesConcerts = concertController.findAllUtilisateur();
        //-- Insert
        assertThat(listeDeTousLesConcerts.size()).isNotEqualTo(0);
    }

    @Test
    @Order(3)
    @Tag("supprimerUnConcert")
    @DisplayName("vérifie qu'un concert a été supprimé à la base de données")
    public void supprimerUnConcert() {
        //-- Arrange
        List<ConcertDate> concertDateList;
        //-- Act
        concertDateList = concertController.supprimerUnConcert(concertDateAjoute.getIdConcertDate());
        System.out.println(concertDateList);
        //-- Insert
        assertThat(concertDateList).isNull();
    }

}
