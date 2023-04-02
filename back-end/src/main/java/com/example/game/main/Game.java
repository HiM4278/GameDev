package com.example.game.main;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class Game {
    @Getter
    private List<Match> matches = new ArrayList<>();
    public static final Configuration configuration = Configuration.instance(Paths.get("Configuration.txt"));

    public UUID CreateMatch(Player host, String roomName, String password, int maxPlayer){
        for (Match m : matches){
            if(m.getRoomName().equals(roomName)) {
                return null;
            }
        }
        Match match = new Match(roomName,password,host,maxPlayer);
        matches.add(match);
        return match.getId();
    }

    public boolean checkMatch(UUID playerID, UUID matchID){
        for (Match m : matches){
            if(m.getId().equals(matchID)) {
                System.out.println(m.getPlayers());
                for (Player p : m.getPlayers()){
                    if(p.getId().equals(playerID)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Match findMatch(UUID matchID){
        for (Match m : matches){
            if(m.getId().equals(matchID)) {
                return m;
            }
        }
        return null;
    }

    public Match findMatch(String matchName){
        for (Match m : matches){
            if(m.getRoomName().equals(matchName)) {
                return m;
            }
        }
        return null;
    }

    public void updateMatches(){
        matches.removeIf(Match::isEnd);
    }

}
