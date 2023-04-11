package com.vpos.server.user;

/*
 * @created 08/04/2023 - 7:06 AM
 * @project server
 * @author Rithy SKUN
 */

import com.vpos.server.business.Business;
import com.vpos.server.business.BusinessRepository;
import com.vpos.server.role.Role;
import com.vpos.server.role.RoleRepository;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BusinessRepository businessRepository;

    @Autowired
    private Validator validator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BusinessRepository businessRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.businessRepository = businessRepository;
    }

    @Override
    public User registerUser(User user) {
        Optional<User> findUserByEmail = userRepository.findUserByEmail(user.getEmail());


        if(findUserByEmail.isPresent()) {
            throw new IllegalStateException("Email id: " + user.getEmail() + " already exists");
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        boolean exists = userRepository.existsById(id);

        if(!exists) {
            throw new IllegalStateException("User with id " + id + " does not exist");
        }
       userRepository.deleteById(id);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUserById(Long id, User user) {
        User _user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("The user id " + id + " does not exists"));

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

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("The user id " + id + " does not exists"));
    }

    @Override
    public List<User> findUserContainingName(@Param("firstname") String firstname, @Param("lastname") String lastname) {
        return userRepository.findUserContainingName(firstname, lastname);
    }

    @Override
    public List<User> findUserEmailContaining(String email) {
        return userRepository.findUserEmailContaining(email);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByRoleName(roleName);

        user.getRoles().add(role);
    }

    @Override
    public void addBusinessToUser(String email, String businessName) {
        User user = userRepository.findByEmail(email);
        Business business = businessRepository.findByName(businessName);

        user.getBusiness().add(business);

    }
}

