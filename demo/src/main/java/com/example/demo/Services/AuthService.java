package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.AuthRes;
import com.example.demo.Models.LoginReq;
import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    public AuthRes register(User user) {

        return null;
    }

    public AuthRes login(LoginReq credentials) {

        return null;
    }

}
