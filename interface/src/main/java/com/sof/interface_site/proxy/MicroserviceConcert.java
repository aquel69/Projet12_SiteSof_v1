package com.sof.interface_site.proxy;

import com.sof.interface_site.model.ConcertDate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "microservice-concert", url = "localhost:9090")
public interface MicroserviceConcert {

    @GetMapping(value = "/TousLesConcerts")
    List<ConcertDate> findAllUtilisateur();

}
