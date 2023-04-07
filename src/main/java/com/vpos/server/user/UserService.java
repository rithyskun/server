package com.vpos.server.user;

import com.vpos.server.role.Role;
import com.vpos.server.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User registerUser(User user) {

        Optional<User> findUserByEmail = userRepository.findUserByEmail(user.getEmail());

        if(!StringUtils.hasText(user.getFirstname())){
            throw new IllegalStateException("First name is required");
        }

        if(!StringUtils.hasText(user.getLastname())) {
            throw new IllegalStateException("Last name is required");
        }

        if(findUserByEmail.isPresent()) {
            throw new IllegalStateException("Email already exists");
        }

//        Role _role = roleRepository.getReferenceById(user.getRoles());

        return userRepository.save(user);
    }

    public void deleteUserById(Long userId) {
        boolean exists = userRepository.existsById(userId);

        if(!exists) {
            throw new IllegalStateException("User with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public User updateUserById(Long userId, User user) {
        User _user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("The user id " + userId + " does not exists"));

        if(_user != null) {
            _user.setFirstname(user.getFirstname());
            _user.setLastname(user.getLastname());
            _user.setEmail(user.getEmail());
            _user.setPassword(user.getPassword());
            _user.setIs_admin(user.getIs_admin());
            _user.setBusiness(user.getBusiness());
            _user.setStatus(user.getStatus());
            _user.setRoles(user.getRoles());
        }

        assert _user != null;
        return userRepository.save(_user);
    }

    public User findUserById(String userId) {
        Long id = Long.parseLong(userId);

        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("The user id " + userId + " does not exists"));
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public List<User> findUserEmailContaining(String email) {
        return userRepository.findUserEmailContaining(email);
    }

    public List<User> findUserContainingName(String name) {
        return userRepository.findUserContainingName(name);
    }
}
