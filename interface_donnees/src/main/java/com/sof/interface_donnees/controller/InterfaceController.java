package com.sof.interface_donnees.controller;

import com.sof.interface_donnees.dao.*;
import com.sof.interface_donnees.model.AlbumInterface;
import com.sof.interface_donnees.model.BiographieInterface;
import com.sof.interface_donnees.model.ConcertInterface;
import com.sof.interface_donnees.model.PhotoInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class InterfaceController {

    @Autowired
    private DaoAccueilInterface daoAccueilInterface;

    @Autowired
    private DaoConcertInterface daoConcertInterface;

    @Autowired
    private DaoBiographieInterface daoBiographieInterface;

    @Autowired
    private DaoAlbumInterface daoAlbumInterface;

    @Autowired
    private DaoPhotoInterface daoPhotoInterface;

    /**
     * récupère l'url de la vidéo youtube dans la base de données
     * @return String
     */
    @GetMapping(value = "/recuperationUrlVideo")
    public String getUrlVideoYoutube(){
        return daoAccueilInterface.getUrlVideoYoutube();
    }

    /**
     * récupère les liens des photos de l'interface concert dans la base de données
     * @param id id
     * @return ConcertInterface
     */
    @GetMapping(value = "/recuperationConcertInterface/{id}")
    public ConcertInterface getConcertInterface(@PathVariable int id){
        ConcertInterface concertInterface = daoConcertInterface.findByIdConcertInterface(id);
        log.info("getConcertInterface - interface du concert : " + concertInterface);
        return concertInterface;
    }

    /**
     * récupère les liens des photos de l'interface biographie et les paragraphes dans la base de données
     * @param id id
     * @return BiographieInterface
     */
    @GetMapping(value = "/recuperationBiographieInterface/{id}")
    public BiographieInterface getBiographieInterface(@PathVariable int id){
        BiographieInterface biographieInterface = daoBiographieInterface.findByIdBiographieInterface(id);

        return biographieInterface;
    }

    /**
     * récupère les liens des photos de l'interface biographie, les liens des chansons
     * et les paragraphes dans la base de données
     * @param id id
     * @return AlbumInterface
     */
    @GetMapping(value = "/recuperationAlbumInterface/{id}")
    public AlbumInterface getAlbumInterface(@PathVariable int id){
        AlbumInterface albumInterface = daoAlbumInterface.findByIdAlbumInterface(id);

        return albumInterface;
    }

    /**
     * récupère les liens des photos des differentes pages du site dans la base de données
     * @param id id
     * @return PhotoInterface
     */
    @GetMapping(value = "/recuperationPhotoInterface/{id}")
    public PhotoInterface getPhotoInterface(@PathVariable int id){
        PhotoInterface photoInterface = daoPhotoInterface.findByIdPhotoInterface(id);

        return photoInterface;
    }


}
