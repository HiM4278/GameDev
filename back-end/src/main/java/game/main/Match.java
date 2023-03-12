package game.main;

import extra.LoopCounter;

import java.io.IOException;
import java.util.ArrayList;

public class Match {
    private int MaxPlayer;
    private String name;
    private String password;
    private Player host;
    private ArrayList<Player> player = new ArrayList<>();
    private Territory territory;
    private Configuration configuration;
    private boolean isPlaying;
    private LoopCounter curPlayerIndex;
    public Match(Configuration configuration,String name,String password, Player host,int maxPlayer){
        this.configuration = configuration;
        this.name = name;
        this.password = password;
        this.host = host;
        this.MaxPlayer = maxPlayer;
        this.territory = new Territory((int)configuration.getM(),(int)configuration.getN());
    }
    public boolean addPlayer(String NamePlay) throws IOException {
        Player p = new Player(NamePlay,this.territory);
        if(player.size() < MaxPlayer){
            player.add(p);
            return true;
        } else {
            return false;
        }
    }
    public void start(){
        
    }
    public void nextPlayer(Player player){
        if(isPlaying){
            player.endTurn();
            curPlayerIndex.increase();
        }
    }
    public String getName(){
        return this.name;
    }
    public boolean isEnd(){
        boolean check = false;
        for (Player p : player ){
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
