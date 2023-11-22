package com.example.demo.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.User;
import com.example.demo.Services.UserService;

@RestController
@RequestMapping("/user")
public class ControllerUser {

    @Autowired
    UserService userService;

    @GetMapping("/getUser/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getUsers")
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public boolean deleteUserById(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }

    @DeleteMapping("/deleteUser")
    public boolean deleteUserByUser(@RequestBody User user) {
        return userService.deleteUserByUser(user);
    }
}
