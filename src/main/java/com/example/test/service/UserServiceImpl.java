package com.example.test.service;

import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String createUser(User user) {
        try {
            userRepository.save(user);
            return "Registration Complete.";
        } catch (Exception ex) {
            System.out.println(ex);
            return "Registration Failed!!!";
        }
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
