package com.sof.concert.model;

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
@Table(name=("concert_date"))
public class ConcertDate {

    /**
     * id de la table concert_date
     */
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="concert_date_id_concert_seq", initialValue = 1, allocationSize = 1)
    @Column(name="id_concert_date")
    private int idConcertDate;

    /**
     * adresse du lieu du concert
     */
    @NonNull
    @Column(name="adresse")
    private String adresse;

    /**
     * date du concert
     */
    @NonNull
    @Column(name="date", columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

    /**
     * nom ou lieu du concert
     */
    @NonNull
    @Column(name="nom_lieu")
    private String nomLieu;

    /**
     * tarif
     */
    @NonNull
    @Column(name="tarif")
    private String tarif;

}
