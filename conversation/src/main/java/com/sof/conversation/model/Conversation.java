package com.sof.conversation.model;

import lombok.Data;
import lombok.NoArgsConstructor;

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
     * utilisateur
     */
    private UtilisateurAuthentification membre;

    /**
     * utilisateur
     */
    private UtilisateurAuthentification interlocuteur;

    /**
     * date du commentaire
     */
    private LocalDateTime dateAjout;
}
