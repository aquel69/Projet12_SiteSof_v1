package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Role {

    /**
     * id de la table utilisateur
     */
    private int idRole;

    /**
     * statut du role
     */
    private String statut;

}
