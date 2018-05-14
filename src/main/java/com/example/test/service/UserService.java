package com.example.test.service;

import com.example.test.model.User;

import java.util.List;

public interface UserService {

    String createUser(User user);
    List<User> getUsers();
}
