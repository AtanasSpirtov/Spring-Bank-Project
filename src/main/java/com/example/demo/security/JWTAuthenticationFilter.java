package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.controller.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.example.demo.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public static final String USER_INFORMATION_HEADER = "User-Information";
    private final AuthenticationManager authenticationManager;

    protected static Logger logger = LoggerFactory.getLogger("JWT");

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl(SIGN_IN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            String userInformationHeader = req.getHeader(USER_INFORMATION_HEADER);

            logger.info("Attempting authentication with user credentials {}", userInformationHeader);

            UserDTO credentials = new ObjectMapper()
                    .readValue(userInformationHeader, UserDTO.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) {
        Object principal = auth.getPrincipal();
        logger.info("Generating token with Prefix: {} , with Principal: {} , with Secret: {} , for time: {}",
                TOKEN_PREFIX, principal, SECRET, EXPIRATION_TIME);

        StringBuilder token = new StringBuilder(TOKEN_PREFIX).append(JWT.create()
                .withSubject(((UserDetails) principal).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes())));

        res.addHeader(SECURITY_HEADER_NAME, token.toString());
    }
}
