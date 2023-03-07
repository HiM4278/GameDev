package game.main;


import extra.GameTimer;
import game.object.CityCrew;
import game.object.Land;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Player {
    private String name;
    private Configuration configuration = new Configuration(Path.of("Z:\\OOP\\Project_UPBEAT\\back-end\\src\\main\\java\\game\\main\\Configuration.txt"));
    private Territory territory = new Territory((int)configuration.getM(),(int)configuration.getN());
    private String state;
    private UUID id;
    private int turn;
    private ConstructionPlan plan;
    private Timestamp endTimeForInitPlan;
    private Land CityCenter;
    private CityCrew crew;
    private LinkedList<Land> land;
    private ArrayList<List<Action>> actions;
    private GameTimer revisionTime,endTimeForPlan;

    public Player(String name) throws IOException {
        this.name = name;
        this.CityCenter = new Land(configuration.getInitCenterDeposit(),this,territory.RandomRegion());
        this.CityCenter.setThisToCenter();
        this.id = UUID.randomUUID();
        this.crew = new CityCrew((int)configuration.getInitBudget(),CityCenter,CityCenter.getPosition());
        this.revisionTime = new GameTimer(configuration.getTimeForRevision());
    }
    public boolean hasLost(Player player){
        if (player.getCityCenter().getDeposit() == 0){
            return true;
        } else {
            return false;
        }
    }
    public void play(){
        CityCenter = new Land(configuration.getMax_dep(),this,territory.RandomRegion());
        state = "Plan";
        revisionTime.start();
    }
    public boolean SubmitPlan(String src){
//        if(plan.)
        return false;
    }
    public void endTurn(){
        state = "Idle";
        crew.SetBasePosition();
    }
    public void CreateLand(Region region){
        land.add(new Land(configuration.getMax_dep(),this,region));
    }
    public Land getCityCenter(){
        return CityCenter;
    }
    public String getState(){
        return state;
    }

}
