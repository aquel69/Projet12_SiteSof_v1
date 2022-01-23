package com.sof.interface_donnees.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name=("concert_interface"))
public class ConcertInterface {

    /**
     * id de la table concert_interface
     */
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="adresse_id_seq_1", initialValue = 1, allocationSize = 1)
    @Column(name="id_concert_interface")
    private int idConcertInterface;

    /**
     * photo 1
     */
    @NonNull
    @Column(name="photo_1")
    private String photo1;

    /**
     * photo 2
     */
    @NonNull
    @Column(name="photo_2")
    private String photo2;

    /**
     * photo 3
     */
    @NonNull
    @Column(name="photo_3")
    private String photo3;

}
