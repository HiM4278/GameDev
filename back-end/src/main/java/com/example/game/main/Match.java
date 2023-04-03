package com.example.game.main;

import com.example.extra.LoopCounter;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Getter
public class Match {
    private final UUID id;
    private final int maxPlayer;
    private final String roomName;
    private String password;
    private Player host;
    private final ArrayList<Player> players = new ArrayList<>();
    private final Territory territory;
    private boolean isPlaying;
    private LoopCounter currPlayerIndex;

    public Match(String roomName,String password, Player host,int maxPlayer){
        this.id = UUID.randomUUID();
        this.roomName = roomName;
        this.password = password;
        this.host = host;
        this.maxPlayer = maxPlayer;
        this.players.add(host);
        this.territory = new Territory((int)Game.configuration.getM(), (int)Game.configuration.getN());
    }

    public UUID addPlayer(String playerName, String password) {
        System.out.println(this.password);
        System.out.println(this.maxPlayer);
        if(this.password.equals(password)) {
            if(players.size() < maxPlayer) {
                Player p = new Player(playerName);
                players.add(p);
                return p.getId();
            } else {
                return null;
            }
        }return null;
    }

    public void start(){
        for(Player player : players) {
            player.initCityCenter(territory.RandomRegion());
        }
        currPlayerIndex = new LoopCounter(players.size());
        isPlaying = true;
    }

    public void nextPlayer(Player player){
        if(isPlaying){
            player.endTurn();
            currPlayerIndex.increase();
        }
    }

    public String getRoomName(){
        return this.roomName;
    }

    public boolean isEnd(){
        boolean check = false;
        for (Player p : players){
            if (p.hasLost()){
                check = true;
            } else {
                check = false;
            }
        }
        return check;
    }

    public Territory getTerritory(){
        return territory;
    }
}
