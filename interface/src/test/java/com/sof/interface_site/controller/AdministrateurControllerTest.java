package com.sof.interface_site.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class AdministrateurControllerTest {

    @InjectMocks
    private AdministrateurController administrateurController;

    @Test
    @Tag("verificationNbCaractereMessageConversation")
    @DisplayName("vérifie que le message fait moins de 2 caractères")
    public void verificationSiNbDeCaractereDuMessageIncorrect() {
        //-- Arrange
        String message = "a";

        //-- Act
        //-- Insert
        assertFalse(administrateurController.verificationNbCaractereMessageConversation(message));
    }

    @Test
    @Tag("verificationNbCaractereMessageConversation")
    @DisplayName("vérifie que le message fait minimum 2 caractères")
    public void verificationSiNbDeCaractereDuMessageCorrect() {
        //-- Arrange
        String message = "aaa";
        //-- Act
        boolean verification = administrateurController.verificationNbCaractereMessageConversation(message);
        //-- Insert
        assertTrue(verification);
    }

    @Test
    @Tag("verificationDate")
    @DisplayName("vérifie si la syntaxe de la date est incorrect")
    public void verificationSiDatecorrect() {
        //-- Arrange
        String date = "17-10-2022";
        //-- Act
        boolean verification = administrateurController.verificationDate(date);
        //-- Insert
        assertTrue(verification);
    }

    @Test
    @Tag("verificationDate")
    @DisplayName("vérifie si la syntaxe de la date est correct")
    public void verificationSiDateIncorrect() {
        //-- Arrange
        String date = "17/10/2022";
        //-- Act
        boolean verification = administrateurController.verificationDate(date);
        //-- Insert
        assertFalse(verification);
    }

}
