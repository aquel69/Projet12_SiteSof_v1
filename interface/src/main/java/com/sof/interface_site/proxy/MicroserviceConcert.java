package com.sof.interface_site.proxy;

import com.sof.interface_site.model.ConcertDate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-concert", url = "localhost:9090")
public interface MicroserviceConcert {

    @GetMapping(value = "/tousLesConcerts")
    List<ConcertDate> findAllUtilisateur();

    @PostMapping(value="/ajouterUnConcert")
    ConcertDate ajouterUnConcert(@RequestBody ConcertDate concertDate);

    @DeleteMapping(value="/supprimerUnConcert/{idConcert}")
    ConcertDate supprimerUnConcert(@PathVariable int idConcert);
}
