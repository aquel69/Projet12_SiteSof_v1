package com.sof.authentification.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sof.authentification.controller.AuthentificationController;
import com.sof.authentification.model.Utilisateur;
import jdk.jshell.execution.Util;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /*private AuthenticationManager authenticationManager;

    @Autowired
    AuthentificationController authentificationController;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        //url appelé par spring pour le login
        setFilterProcessesUrl("/Login");
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        Utilisateur utilisateur = null;

        try{
            utilisateur = new ObjectMapper().readValue(request.getInputStream(), Utilisateur.class);
        }
        catch (JsonParseException e)
        {
            e.printStackTrace();
        }

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (utilisateur.getUsername(), utilisateur.getMotDePasse()));
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
                                            throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();

        List<String> roles = new ArrayList<>();

        user.getAuthorities().forEach(au -> {
            roles.add(au.getAuthority());
        });

        String accessToken = JWT.create().
                withSubject(user.getUsername()).
                withArrayClaim("roles", roles.toArray(new String[roles.size()])).
                withExpiresAt(new Date(System.currentTimeMillis()+ConstantParameter.TIME_1_DAYS)).
                withIssuer(request.getRequestURL().toString()).
                sign(Algorithm.HMAC256(ConstantParameter.SECRET));

        response.setHeader("Authorization", accessToken);

        *//*Map<String, String > tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        *//**//*tokens.put("refreshToken", refreshToken);*//**//*
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);*//*
    }*/
}



        /*String refreshToken = JWT.create().
                withSubject(user.getUsername()).
                withExpiresAt(new Date(System.currentTimeMillis()+ConstantParameter.TIME_10_DAYS)).
*//*                withIssuer(request.getRequestURL().toString()).*//*
                sign(Algorithm.HMAC256(ConstantParameter.SECRET));*/


//response.setHeader("refreshToken", refreshToken);
//nous mettons les tokens dans le body