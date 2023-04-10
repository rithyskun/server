package com.vpos.server.user;

/*
 * @created 08/04/2023 - 6:58 AM
 * @project server
 * @author Rithy SKUN
 */

import java.util.List;

public interface UserService {
    User registerUser(User user);
    void deleteUserById(Long id);
    List<User> getUsers();

    User updateUserById(Long id, User user);

    User findUserById(Long id);

    List<User> findUserContainingName(String firstname, String lastname);

    List<User> findUserEmailContaining(String email);

    void addRoleToUser(String email, String roleName);
}
