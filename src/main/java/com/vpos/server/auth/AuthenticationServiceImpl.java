package com.vpos.server.auth;

/**
 * @created 12/04/2023 - 5:11 AM
 * @project server
 * @author Rithy SKUN
 **/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vpos.server.security.JwtService;
import com.vpos.server.token.Token;
import com.vpos.server.token.TokenRepository;
import com.vpos.server.token.TokenType;
import com.vpos.server.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.vpos.server.user.User;

import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final TokenRepository tokenRepository;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        User user = new User(request.getFirstname(),
                request.getLastname(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                true, new ArrayList<>(),
                true, new ArrayList<>(),
                new Date(), new Date());
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return new AuthenticationResponse(jwtToken, refreshToken);

    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        System.out.println(request);

       try {
           var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow();

           var jwtToken = jwtService.generateToken(user);
           var refreshToken = jwtService.generateRefreshToken(user);

           //Revoked all the contain user in token table
           revokeAllUsersToken(user);

           //Generate token as new one
           saveUserToken(user, jwtToken);

           return new AuthenticationResponse(jwtToken, refreshToken);

       } catch (Exception ex) {
           throw new RuntimeException(ex);
       }

    }

    @Override
    @Transactional
    public void saveUserToken(User user, String jwtToken) {
        try {
            var _token = new Token(jwtToken, TokenType.BEARER , false, false, user);

            tokenRepository.save(_token);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void revokeAllUsersToken(User user) {
        try {
            var validToken = tokenRepository.findAllValidTokenByUser(user.getId());

            if(validToken.isEmpty()) {
                return;
            }

            validToken.forEach(token -> {
                token.setExpired(true);
                token.setRevoked(true);
            });

            tokenRepository.saveAll(validToken);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String email;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalStateException("The auth header missed match");
        }

        refreshToken = authHeader.substring(7);
        email = jwtService.extractUsername(refreshToken);

        if (email != null) {
            var user = this.userRepository.findUserByEmail(email).orElseThrow();

            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUsersToken(user);

                saveUserToken(user, accessToken);

                var authResponse = new AuthenticationResponse(accessToken, refreshToken);

                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
