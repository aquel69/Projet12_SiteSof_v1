package com.sof.interface_site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

public class AlbumInterface {

    /**
     * id de la table album_interface
     */
    private int idAlbumInterface;

    /**
     * 1er sous titre
     */
    private String sousTitre1;

    /**
     * 2ème sous titre
     */
    private String sousTitre2;

    /**
     * photo 1
     */
    private String photoAlbum;

    /**
     * photo 2
     */
    private String photoCD;

    /**
     * 1er lien chanson
     */
    private String chanson1;

    /**
     * 2ème lien chanson
     */
    private String chanson2;

    /**
     * 3ème lien chanson
     */
    private String chanson3;

    /**
     * 4ème lien chanson
     */
    private String chanson4;

    /**
     * 1er titre chanson
     */
    private String titreChanson1;

    /**
     * 2ème titre chanson
     */
    private String titreChanson2;

    /**
     * 3ème titre chanson
     */
    private String titreChanson3;

    /**
     * 4ème titre chanson
     */
    private String titreChanson4;

    /**
     * tarif de l'EP
     */
    private String tarifEP;

    public int getIdAlbumInterface() {
        return idAlbumInterface;
    }

    public void setIdAlbumInterface(int idAlbumInterface) {
        this.idAlbumInterface = idAlbumInterface;
    }

    public String getSousTitre1() {
        return sousTitre1;
    }

    public void setSousTitre1(String sousTitre1) {
        this.sousTitre1 = sousTitre1;
    }

    public String getSousTitre2() {
        return sousTitre2;
    }

    public void setSousTitre2(String sousTitre2) {
        this.sousTitre2 = sousTitre2;
    }

    public String getPhotoAlbum() {
        return photoAlbum;
    }

    public void setPhotoAlbum(String photoAlbum) {
        this.photoAlbum = photoAlbum;
    }

    public String getPhotoCD() {
        return photoCD;
    }

    public void setPhotoCD(String photoCD) {
        this.photoCD = photoCD;
    }

    public String getChanson1() {
        return chanson1;
    }

    public void setChanson1(String chanson1) {
        this.chanson1 = chanson1;
    }

    public String getChanson2() {
        return chanson2;
    }

    public void setChanson2(String chanson2) {
        this.chanson2 = chanson2;
    }

    public String getChanson3() {
        return chanson3;
    }

    public void setChanson3(String chanson3) {
        this.chanson3 = chanson3;
    }

    public String getChanson4() {
        return chanson4;
    }

    public void setChanson4(String chanson4) {
        this.chanson4 = chanson4;
    }

    public String getTitreChanson1() {
        return titreChanson1;
    }

    public void setTitreChanson1(String titreChanson1) {
        this.titreChanson1 = titreChanson1;
    }

    public String getTitreChanson2() {
        return titreChanson2;
    }

    public void setTitreChanson2(String titreChanson2) {
        this.titreChanson2 = titreChanson2;
    }

    public String getTitreChanson3() {
        return titreChanson3;
    }

    public void setTitreChanson3(String titreChanson3) {
        this.titreChanson3 = titreChanson3;
    }

    public String getTitreChanson4() {
        return titreChanson4;
    }

    public void setTitreChanson4(String titreChanson4) {
        this.titreChanson4 = titreChanson4;
    }

    public String getTarifEP() {
        return tarifEP;
    }

    public void setTarifEP(String tarifEP) {
        this.tarifEP = tarifEP;
    }
}
