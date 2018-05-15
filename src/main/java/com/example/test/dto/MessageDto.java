package com.example.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {

    private String message;

    public MessageDto setMessage(String message) {
        this.message = message;
        return this;
    }
}
