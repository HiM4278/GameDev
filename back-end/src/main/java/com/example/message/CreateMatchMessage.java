package com.example.message;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateMatchMessage {
    private int maxPlayer;
    private String roomName;
    private String password;
    private String host;
}
