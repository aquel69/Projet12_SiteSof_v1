package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UtilisateurAuthentification {

    private int idMembre;

    /**
     * email du membre
     */
    private String email;

    /**
     * role de l'utilisateur
     */
    private int role;

}
