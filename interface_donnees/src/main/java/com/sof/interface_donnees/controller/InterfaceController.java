package com.sof.interface_donnees.controller;

import com.sof.interface_donnees.dao.*;
import com.sof.interface_donnees.model.AlbumInterface;
import com.sof.interface_donnees.model.BiographieInterface;
import com.sof.interface_donnees.model.ConcertInterface;
import com.sof.interface_donnees.model.PhotoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/recuperationUrlVideo")
    public String getUrlVideoYoutube(){
        return daoAccueilInterface.getUrlVideoYoutube();
    }

    @GetMapping(value = "/recuperationConcertInterface/{id}")
    public ConcertInterface getConcertInterface(@PathVariable int id){
        ConcertInterface concertInterface = daoConcertInterface.findByIdConcertInterface(id);

        return concertInterface;
    }

    @GetMapping(value = "/recuperationBiographieInterface/{id}")
    public BiographieInterface getBiographieInterface(@PathVariable int id){
        BiographieInterface biographieInterface = daoBiographieInterface.findByIdBiographieInterface(id);

        return biographieInterface;
    }

    @GetMapping(value = "/recuperationAlbumInterface/{id}")
    public AlbumInterface getAlbumInterface(@PathVariable int id){
        AlbumInterface albumInterface = daoAlbumInterface.findByIdAlbumInterface(id);

        return albumInterface;
    }

    @GetMapping(value = "/recuperationPhotoInterface/{id}")
    public PhotoInterface getPhotoInterface(@PathVariable int id){
        PhotoInterface photoInterface = daoPhotoInterface.findByIdPhotoInterface(id);

        return photoInterface;
    }


}
