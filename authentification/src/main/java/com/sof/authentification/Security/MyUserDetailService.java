package com.sof.authentification.Security;

import org.springframework.stereotype.Service;

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
