package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ConcertDate {

    /**
     * id de la table concert_date
     */
    private int idConcertDate;

    /**
     * adresse du lieu du concert
     */
    private String adresse;

    /**
     * date du concert
     */
    private LocalDateTime date;

    /**
     * nom ou lieu du concert
     */
    private String nomLieu;

    /**
     * tarif
     */
    private String tarif;



}
