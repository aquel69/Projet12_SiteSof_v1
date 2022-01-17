package com.sof.authentification.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sof.authentification.model.Utilisateur;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwt = httpServletRequest.getHeader("accessToken");

        if (jwt == null || !jwt.startsWith(ConstantParameter.PREFIX)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(ConstantParameter.SECRET)).build();

        jwt = jwt.substring(ConstantParameter.PREFIX.length());

        DecodedJWT decodedJWT = verifier.verify(jwt);

        String username = decodedJWT.getSubject();

        List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        for (String r : roles)
            authorities.add(new SimpleGrantedAuthority(r));

        UsernamePasswordAuthenticationToken user =
                new UsernamePasswordAuthenticationToken(username, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(user);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
