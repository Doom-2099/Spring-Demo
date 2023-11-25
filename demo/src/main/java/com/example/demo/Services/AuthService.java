package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.Models.AuthRes;
import com.example.demo.Models.LoginReq;
import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    public AuthRes register(User user) {
        User _user = User
                .builder()
                .email(user.getEmail())
                .pass(user.getPassword())
                .name(user.getName())
                .role(user.getRole())
                .build();

        userRepository.save(_user);

        return AuthRes
                .builder()
                .token(jwtService.getToken(_user))
                .build();
    }

    public AuthRes login(LoginReq credentials) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));

        UserDetails userDetails = userRepository.findByEmail(credentials.getUsername()).orElseThrow();
        String token = jwtService.getToken(userDetails);
        return AuthRes
                .builder()
                .token(token)
                .build();
    }

}
