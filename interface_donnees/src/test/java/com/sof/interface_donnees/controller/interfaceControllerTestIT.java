package com.sof.interface_donnees.controller;

import com.sof.interface_donnees.model.AlbumInterface;
import com.sof.interface_donnees.model.PhotoInterface;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class interfaceControllerTestIT {

    @Autowired
    InterfaceController interfaceController;

    PhotoInterface photoInterface;

    AlbumInterface albumInterface;

    @BeforeAll
    public void initTest() {
        photoInterface = new PhotoInterface();
        albumInterface = new AlbumInterface();
    }

    @AfterAll
    public void afterTest(){
        photoInterface = null;
        albumInterface = null;
    }

    @Test
    @Tag("getUrlVideoYoutube")
    @DisplayName("vérifie que l'url youtube a bien été récupéré dans la base de données")
    public void recuperationURLYoutubeTest() {
        //-- Arrange
        String url;
        //-- Act
        url = interfaceController.getUrlVideoYoutube();
        //-- Insert
        assertThat(url).isEqualTo("https://www.youtube.com/embed/yRWCAFs1oY8?controls=0");
    }

    @Test
    @Tag("getPhotoInterface")
    @DisplayName("vérifie que la photoInterface a bien été récupéré dans la base de données")
    public void recuperationPhotoInterfaceTest() {
        //-- Arrange
        //-- Act
        photoInterface = interfaceController.getPhotoInterface(1);
        //-- Insert
        assertThat(photoInterface.getPhotoContact()).isEqualTo("sof_contact.jpg");
    }

    @Test
    @Tag("getAlbumInterface")
    @DisplayName("vérifie que l'albumInterface' a bien été récupéré dans la base de données")
    public void getAlbumInterface() {
        //-- Arrange
        //-- Act
        albumInterface = interfaceController.getAlbumInterface(1);
        //-- Insert
        assertThat(albumInterface.getChanson1()).isEqualTo("LA-LUNA-MIX1.mp3");
    }
}
