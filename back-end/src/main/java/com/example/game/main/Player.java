package com.example.game.main;


import com.example.exeption.SyntaxErrorException;
import com.example.extra.GameTimer;
import com.example.game.object.CityCrew;
import com.example.game.object.Land;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import static com.example.game.main.Game.configuration;

@Getter
public class Player {
    private final String playerName;
    private Match match;
    private String state;
    private final UUID id;
    private String color;
    private int turn = 0;
    private final ConstructionPlan plan;
    private Land cityCenter;
    private CityCrew crew;
    private LinkedList<Land> lands = new LinkedList<>();
    private ArrayList<List<Action>> actions = new ArrayList<>();
    private GameTimer revisionTime,TimeForInitPlan;

    private boolean initPlanAlready;

    public Player(String playerName) {
        this.playerName = playerName;
        this.id = UUID.randomUUID();
        this.color = randomColor();
        this.plan = new ConstructionPlan(this);
        this.revisionTime = new GameTimer(configuration.getTimeForRevision());
        this.TimeForInitPlan = new GameTimer(configuration.getTimeForFirstPlan());
    }

    public boolean hasLost(){
        if (this.getCityCenter().getDeposit() == 0){
            return true;
        } else {
            return false;
        }
    }

    public void play(){
        state = "Plan";
        TimeForInitPlan.start();
        this.turn++;
    }

    public String SubmitPlan(String src) {
        try {
            System.out.println(src);
            plan.updatePlan(src);
            String err = plan.parse();
            endTurn();
            return err;
        } catch (SyntaxErrorException e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String RunPlan() {
        return plan.run();
    }

    public void endTurn(){
        state = "Idle";
        crew.moveToCityCenter();
    }

    private Land createLand(Region region, boolean isCityCenter){
        if(region.getLand() == null) {
            Land land;
            if(isCityCenter){
                land = new Land(this, region, true);
            }else {
                land = new Land(this, region);
            }
            lands.add(land);
            region.setLand(land);
            return land;
        }
        return null;
    }

    public Land createLand(Region region){
        return createLand(region, false);
    }

    public void relocate(Region region){
        if(region.getLand() != null && region.getLand().getOwner() == this) {
            region.getLand().setToCenter();
            this.cityCenter.setToNormalLand();
            this.cityCenter = region.getLand();
        }
    }

    public void initCityCenter(Region region){
        if(this.cityCenter == null && region.getLand() == null){
            this.cityCenter = createLand(region,true);
            this.initCityCrew();
        }
    }

    public Land getCityCenter(){
        return cityCenter;
    }

    public void soldLand(Land land){
        land.getPosition().setLand(null);
        lands.remove(land);
    }

    private void initCityCrew(){
        if(this.crew == null) {
            this.crew = new CityCrew(this);
        }
    }

    public String getState(){
        return state;
    }

    public LinkedList<Land> getAllLand(){
        return lands;
    }

    private String randomColor() {
        // Create a random number generator
        Random random = new Random();

        // Generate random values for the red, green, and blue components
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        // Create a Color object with the random RGB values
        Color randomColor = new Color(red, green, blue);

        return toHexString(randomColor);
    }

    // Convert a Color object to a hexadecimal color code string
    private String toHexString(Color color) {
        String hex = Integer.toHexString(color.getRGB() & 0xffffff);
        if (hex.length() < 6) {
            hex = "0" + hex;
        }
        hex = "#" + hex;
        return hex;
    }

}
