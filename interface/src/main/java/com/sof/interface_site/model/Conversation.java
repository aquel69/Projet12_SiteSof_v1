package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Conversation {

    /**
     * id de la table commentaire
     */
    private int idCommentaire;

    /**
     * message
     */
    private String message;

    /**
     * membre concernĂ©
     */
    private UtilisateurAuthentification membre;

    /**
     * interlocuteur
     */
    private UtilisateurAuthentification interlocuteur;

    /**
     * date du commentaire
     */
    private LocalDateTime dateAjout;


}
