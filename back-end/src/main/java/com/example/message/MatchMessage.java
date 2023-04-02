package com.example.message;

import com.example.game.main.Match;
import lombok.Getter;

import java.util.UUID;

@Getter
public class MatchMessage {
    private int maxPlayer;
    private int numPlayer;
    private UUID hostID;
    private boolean isPlaying;

    public MatchMessage(Match match){
        this.maxPlayer = match.getMaxPlayer();
        this.numPlayer = match.getPlayers().size();
        this.hostID = match.getHost().getId();
        this.isPlaying = match.isPlaying();
    }
}
