package com.sof.concert.controller;

import com.sof.concert.dao.DaoConcertDate;
import com.sof.concert.model.ConcertDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConcertController {

    @Autowired
    DaoConcertDate daoConcertDate;

    /**
     * récupère une liste de tous les concerts dans la base de données
     * @return List ConcertDate
     */
    @GetMapping(value = "/tousLesConcerts")
    public List<ConcertDate> findAllUtilisateur() {
        List<ConcertDate> concerts = daoConcertDate.findAllUtilisateurByDate();

        return concerts;
    }

    /**
     * ajoute un concert dans la base de données
     * @param concertDate concertDate
     */
    @PostMapping(value="/ajouterUnConcert")
    public void ajouterUnConcert(@RequestBody ConcertDate concertDate) {
        daoConcertDate.save(concertDate);
    }

    /**
     *  supprime un concert dans la base de données
     * @param idConcert idConcert
     */
    @DeleteMapping(value="/supprimerUnConcert/{idConcert}")
    public void supprimerUnConcert(@PathVariable int idConcert) {
        daoConcertDate.deleteById(idConcert);
    }

}
