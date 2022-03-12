package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ConversationBDD {

        /**
         * id de la table commentaire
         */
        private int idCommentaire;

        /**
         * commentaire
         */
        private String message;

        /**
         * id membre
         */
        private int membre;

        /**
         * id interlocuteur
         */
        private int interlocuteur;

        /**
         * date du commentaire
         */
        private LocalDateTime dateAjout;

}
