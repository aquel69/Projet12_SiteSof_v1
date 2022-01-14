package com.sof.authentification;

import com.sof.authentification.model.Adresse;
import com.sof.authentification.model.Utilisateur;
import com.sof.authentification.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class AuthentificationApplication extends SpringBootServletInitializer {

	@Autowired
	UserServiceImpl userService;

	LocalDateTime dateTime = LocalDateTime.now();

	public static void main(String[] args) {
		SpringApplication.run(AuthentificationApplication.class, args);
	}

	/*@PostConstruct
	void init_utilisateur(){
		Adresse adresse = new Adresse();

		adresse.setIdAdresse(1);

		*//*userService.addAdresse(adresse);*//*
		userService.saveUtilisateur(new Utilisateur("Rut", "Sophie",  "Sof", adresse, "sophierut@gmail.com", "123", dateTime));
		userService.addRoleToUtilisateur("sophierut@gmail.com", "administrateur");
	}
*/
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
