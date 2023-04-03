package com.example.message;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ConstructionMessage {
    private UUID matchID;
    private UUID playerID;
    private String code;
}
