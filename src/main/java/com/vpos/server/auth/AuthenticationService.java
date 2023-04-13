package com.vpos.server.auth;

/*
 * @created 12/04/2023 - 5:10 AM
 * @project server
 * @author Rithy SKUN
 */

import com.vpos.server.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse login(AuthenticationRequest request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void saveUserToken(User user, String token);

    void revokeAllUsersToken(User user);
}
