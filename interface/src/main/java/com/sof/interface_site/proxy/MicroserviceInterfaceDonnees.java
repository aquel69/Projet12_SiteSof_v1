package com.sof.interface_site.proxy;

import com.sof.interface_site.model.AlbumInterface;
import com.sof.interface_site.model.BiographieInterface;
import com.sof.interface_site.model.ConcertInterface;
import com.sof.interface_site.model.PhotoInterface;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-interface-donnees", url = "localhost:9092")
public interface MicroserviceInterfaceDonnees {

    @GetMapping(value = "/recuperationUrlVideo")
    String getUrlVideoYoutube();

    @GetMapping(value = "/recuperationConcertInterface/{id}")
    ConcertInterface getConcertInterface(@PathVariable int id);

    @GetMapping(value = "/recuperationBiographieInterface/{id}")
    BiographieInterface getBiographieInterface(@PathVariable int id);

    @GetMapping(value = "/recuperationAlbumInterface/{id}")
    AlbumInterface getAlbumInterface(@PathVariable int id);

    @GetMapping(value = "/recuperationPhotoInterface/{id}")
    PhotoInterface getPhotoInterface(@PathVariable int id);

}
