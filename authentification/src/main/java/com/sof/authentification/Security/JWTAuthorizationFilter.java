package com.sof.authentification.Security;

import org.springframework.stereotype.Component;

@Component
public class JWTAuthorizationFilter /*extends OncePerRequestFilter*/ {

    /*@Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwt = httpServletRequest.getHeader("Authorization");

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
    }*/
}
