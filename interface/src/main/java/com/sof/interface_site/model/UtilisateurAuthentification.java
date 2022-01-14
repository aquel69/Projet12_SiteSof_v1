package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UtilisateurAuthentification {

    private int idMembre;

    /**
     * email du membre
     */
    private String email;

    /**
     * username de l'utilisateur
     */
    private String username;

    /**
     * role de l'utilisateur
     */
    private int role;

}
