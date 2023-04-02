package com.example;

import com.example.game.main.Game;
import com.example.game.main.Match;
import com.example.game.main.Player;
import com.example.message.CreateMatchMessage;
import com.example.message.JoinMatchMessage;
import com.example.message.MatchMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
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
    public String createMatch(@RequestBody CreateMatchMessage match) {
        Player host = new Player(match.getHost());
        UUID matchID = game.CreateMatch(host, match.getRoomName(), match.getPassword(), match.getMaxPlayer());
        if(matchID != null){
            return "{\"ok\":true, \"playerID\":\""+host.getId()+"\""+",\"matchID\":\""+matchID+"\"}";
        }else {
            return "{\"ok\":false}";
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping(value = "/match/join")
    public String joinMatch(@RequestBody JoinMatchMessage message) {
        Match match = game.findMatch(message.getRoomName());
        if(match != null) {
            System.out.println(match.getId());
            UUID playerID = match.addPlayer(message.getPlayerName(), message.getPassword());
            if(playerID != null){
                messageSender.convertAndSend("/topic/match/"+match.getId(), new MatchMessage(match));
                return "{\"ok\":true, \"playerID\":\""+playerID+"\""+",\"matchID\":\""+match.getId()+"\"}";
            }else {
                return "{\"ok\":false}";
            }
        }else {
            return "{\"ok\":false}";
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/match/check", method = RequestMethod.GET)
    public String checkMatch(@RequestParam UUID playerID, @RequestParam UUID matchID) {
        if(game.checkMatch(playerID, matchID)){
            return "{\"ok\":true}";
        }else {
            return "{\"ok\":false}";
        }
    }

    @MessageMapping("/match/{id}")
    public String ds() {
        return "greeting";
    }

    @SubscribeMapping("/match/{matchID}")
    public MatchMessage sendInitialMatch(@DestinationVariable UUID matchID) {
        Match match = game.findMatch(matchID);
        if(match != null) {
            return new MatchMessage(match);
        }else {
            return null;
        }
    }
}