package com.example.demo.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User insertUser(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public boolean deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }
    }

    public boolean deleteUserByUser(User user) {
        try {
            userRepository.delete(user);
            return true;
        } catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }
    }
}
