package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

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
    @Size(min=5, max = 100, message = "L\'adresse doit être compris entre 5 et 100 caractères")
    private String adresse;

    /**
     * complément de l'adresse
     */
    @Size(min=0, max = 100, message = "Le complément d'adresse' doit être compris entre 0 et 100 caractères")
    private String complementAdresse;

    /**
     * code postal de la ville
     */
    @Size(min=2, max = 10, message = "Le code postal doit être compris entre 2 et 10 caractères")
    private String codePostal;

    /**
     * ville
     */
    @Size(min=2, max = 50, message = "La ville doit être comprise entre 2 et 50 caractères")
    private String ville;

}
