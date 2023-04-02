package com.example;

import lombok.Getter;

import java.util.UUID;

@Getter
public class MatchMessage {
    private int maxPlayer;
    private String roomName;
    private String password;
    private String host;
}
