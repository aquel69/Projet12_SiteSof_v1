package com.sof.interface_site.model;

import lombok.*;

@Data
@AllArgsConstructor
public class Role {

    public Role(){

    }

    /**
     * id de la table utilisateur
     */
    private int idRole;

    /**
     * statut du role
     */
    private String statut;

}
