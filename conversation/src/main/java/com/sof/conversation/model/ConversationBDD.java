package com.sof.conversation.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name=("conversation"))
public class ConversationBDD {

        /**
         * id de la table commentaire
         */
        @Id
        @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
        @SequenceGenerator(name="project_generator", sequenceName="commentaire_id_commentaire_seq", initialValue = 1, allocationSize = 1)
        @Column(name="id_conversation")
        private int idCommentaire;

        /**
         * commentaire
         */
        @NonNull
        @Column(name="message")
        private String message;

        /**
         * id membre
         */
        @NonNull
        @Column(name="membre_id")
        private int membre;

        /**
         * id interlocuteur
         */
        @NonNull
        @Column(name="interlocuteur_id")
        private int interlocuteur;

        /**
         * date du commentaire
         */
        @NonNull
        @Column(name="date_ajout", columnDefinition = "TIMESTAMP WITH TIME ZONE")
        private LocalDateTime dateAjout;

}
