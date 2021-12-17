package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Adresse {

    /**
     * id du de la table adresse
     */
    private int idAdresse;

    /**
     * adresse
     */
    private String adresse;

    /**
     * complément de l'adresse
     */
    private String complementAdresse;

    /**
     * code postal de la ville
     */
    private String codePostal;

    /**
     * ville
     */
    private String ville;

}
