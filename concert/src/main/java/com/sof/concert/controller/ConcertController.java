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

    @GetMapping(value = "/tousLesConcerts")
    public List<ConcertDate> findAllUtilisateur() {
        List<ConcertDate> concerts = daoConcertDate.findAllUtilisateurByDate();

        return concerts;
    }

    @PostMapping(value="/ajouterUnConcert")
    public void ajouterUnConcert(@RequestBody ConcertDate concertDate) {
        daoConcertDate.save(concertDate);
    }

    @DeleteMapping(value="/supprimerUnConcert/{idConcert}")
    public void supprimerUnConcert(@PathVariable int idConcert) {
        daoConcertDate.deleteById(idConcert);
    }

}
