package com.example.message;

import lombok.Getter;

@Getter
public class JoinMatchMessage {
    private String roomName;
    private String password;
    private String playerName;
}
