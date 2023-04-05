package com.vpos.server.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public List<User> getUsers(@RequestParam Map<String,String> allParams) {
        System.out.println("Rithy" + allParams);
       return userService.getUsers();
    }

    @PostMapping
    public void registerUser(@RequestBody User user) {
        userService.registerUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Long id,
                           @RequestBody User user) {
        userService.updateUserById(id, user);
    }
}
