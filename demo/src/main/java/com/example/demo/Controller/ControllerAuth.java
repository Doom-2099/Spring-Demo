package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.AuthRes;
import com.example.demo.Models.LoginReq;
import com.example.demo.Models.User;
import com.example.demo.Services.AuthService;

@RestController
@RequestMapping("/auth")
public class ControllerAuth {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthRes> createUser(@RequestBody User user) {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthRes> login(@RequestBody LoginReq credentials) {
        return ResponseEntity.ok(authService.login(credentials));
    }
}
