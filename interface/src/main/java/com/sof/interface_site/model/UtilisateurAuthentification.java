package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UtilisateurAuthentification {

    private int idUtilisateur;

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
    private List<Role> roles = new ArrayList<>();
}
