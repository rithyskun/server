package com.vpos.server.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
       return userService.getUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        System.out.println("Rithy " + user);
      User _user = userService.registerUser(user);
      return ResponseEntity.ok(_user);
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("The user id " + id + " has been removed.", HttpStatus.OK);
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long id,
                           @RequestBody User user) {
      User _user = userService.updateUserById(id, user);

      return ResponseEntity.ok(_user);
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<User> findOneUser(@PathVariable("userId") String id)  {
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
}
