package com.sof.authentification.Security;

import com.sof.authentification.dao.DaoUtilisateur;
import com.sof.authentification.model.Utilisateur;
import com.sof.authentification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailService /*implements UserDetailsService*/ {

    /*@Autowired
    DaoUtilisateur daoUtilisateur;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utilisateur utilisateur = daoUtilisateur.findByUsername(username);

        if (utilisateur == null)
            throw new UsernameNotFoundException("Utilisateur introuvable");

        List<GrantedAuthority> auths = new ArrayList<>();

        utilisateur.getRoles().forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getStatut());
            auths.add(authority);
        });

        return new User(utilisateur.getUsername(), utilisateur.getMotDePasse(), auths);
    }*/

}
