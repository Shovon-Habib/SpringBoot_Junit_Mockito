package com.example.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto{
    private Long id;
    private String name;
    private String email;

    public UserDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserDto() {
    }

    public UserDto setId(Long id) {
        this.id = id;
        return this;
    }

    public UserDto setName(String name) {
        this.name = name;
        return this;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }
}
