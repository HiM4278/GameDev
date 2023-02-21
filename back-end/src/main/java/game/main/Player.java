package game.main;

import game.object.CityCrew;
import game.object.Land;

import javax.swing.*;
import java.util.LinkedList;

public class Player {
    private String name;
    private ConstructionPlan plan;

    private CityCrew crew;
    private LinkedList<Land> land;
    private LinkedList<Action> actions;
    public Player(String name){
        this.name = name;
    }

}
