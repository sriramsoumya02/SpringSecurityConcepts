package com.soumya.springsecurity.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Collectors;

public class JWTUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public JWTUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        // super(authenticationManager);
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Authentication authenticate = null;
        try {
            UsernameAndPasswordAuthenticationRequest usernameAndPasswordAuthenticationRequest = new ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(usernameAndPasswordAuthenticationRequest.getUsername(),
                    usernameAndPasswordAuthenticationRequest.getPassword());

            authenticate = authenticationManager.authenticate(authentication);
            return authenticate;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authenticate;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String key = "secreatsecreatsecreatsecreatsecreatsecreatsecreatsecreatsecreat";
        System.out.println(" authorities " + authResult.getAuthorities().toArray());
        Algorithm algorithm = Algorithm.HMAC256(key);
        String token = JWT.create().withSubject(authResult.getName())
                .withClaim("authorities", authResult.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                .withIssuedAt(new Date())
                .withExpiresAt(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .sign(algorithm);
        response.addHeader("Authorization", "Bearer " + token);
    }
}
