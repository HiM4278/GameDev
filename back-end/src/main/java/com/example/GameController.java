package com.example;

import com.example.game.main.Game;
import com.example.game.main.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class GameController {
    @Autowired
    private SimpMessagingTemplate messageSender;

    @Autowired
    private Game game;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/match/create")
    public String greeting(@RequestBody MatchMessage match) {
        Player host = new Player(match.getHost());
        UUID matchID = game.CreateMatch(host, match.getRoomName(), match.getPassword(), match.getMaxPlayer());
        if(matchID != null){
            return "{\"ok\":true, \"playerID\":\""+host.getId()+"\""+",\"matchID\":\""+matchID+"\"}";
        }else {
            return "{\"ok\":false}";
        }
    }

    @MessageMapping("/employees/{id}")
    public String ds() {
        return "greeting";
    }
}