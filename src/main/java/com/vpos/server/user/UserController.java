package com.vpos.server.user;

import com.vpos.server.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
       return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user) {
      URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users").toUriString());
      return ResponseEntity.created(uri).body(userService.registerUser(user));
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long id) {

        return new ResponseEntity<>("The user id " + id + " has been removed.", HttpStatus.OK);
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long id,
                           @RequestBody User user) {
      User _user = userService.updateUserById(id, user);

      return ResponseEntity.ok(_user);
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<User> findOneUser(@PathVariable("userId") Long id)  {
        User _user = userService.findUserById(id);
        return ResponseEntity.ok(_user);
    }

    @GetMapping(path = "/")
    @ResponseBody
    public ResponseEntity<List<User>> findUserNameContaining(@RequestParam(required = false) String email, @RequestParam(required = false) String name) {
        if (StringUtils.hasText(name)) {
            List<User> _name = userService.findUserContainingName(name);
            return ResponseEntity.ok(_name);
        } else if(StringUtils.hasText(email)) {
            List<User> _email = userService.findUserEmailContaining(email);
            return ResponseEntity.ok(_email);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
}

@Repository
class RoleToUserForm {
    private String email;
    private String roleName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}