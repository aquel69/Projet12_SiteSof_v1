package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Commentaire {

    /**
     * id de la table commentaire
     */
    private int idCommentaire;

    /**
     * commentaire
     */
    private String commentaire;

    /**
     * utilisateur
     */
    private Membre membre;

    /**
     * utilisateur
     */
    private Membre interlocuteur;

    /**
     * date du commentaire
     */
    private LocalDateTime dateAjout;


}
