package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
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
    @Size(min=5, max = 80, message = "L'' adresse doit être compris entre 5 et 80 caractères")
    private String adresse;

    /**
     * date du concert
     */
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime date;

    /**
     * nom ou lieu du concert
     */
    @Size(min=2, max = 35, message = "Le nom du lieu doit être compris entre 2 et 35 caractères")
    private String nomLieu;

    /**
     * tarif
     */
    @Size(min=2, max = 15, message = "Le tarif doit être compris entre 2 et 15 caractères")
    private String tarif;



}
