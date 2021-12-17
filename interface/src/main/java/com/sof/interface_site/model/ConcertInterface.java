package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConcertInterface {

    /**
     * id de la table concert_interface
     */
    private int idConcertInterface;

    /**
     * photo 1
     */
    private String photo1;

    /**
     * photo 2
     */
    private String photo2;

    /**
     * photo 3
     */
    private String photo3;

}
