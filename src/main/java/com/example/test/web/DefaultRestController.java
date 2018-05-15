package com.example.test.web;

import com.example.test.dto.MessageDto;
import com.example.test.dto.UserDto;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DefaultRestController {

    private final UserService userService;

    @Autowired
    public DefaultRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/rest/")
    public ResponseEntity<List<UserDto>> getUsers(){
        List<UserDto> users = this.userService.getUsers().stream()
                .map(user -> {
                    return new UserDto()
                            .setId(user.getId())
                            .setEmail(user.getEmail())
                            .setName(user.getName());
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @PostMapping("/rest/new")
    public ResponseEntity<MessageDto> newUser(){
        return ResponseEntity.ok(new MessageDto().setMessage("Successful!!"));
    }
}

