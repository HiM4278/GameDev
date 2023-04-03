package com.example.game.object;

import com.example.extra.Direction;
import com.example.game.main.Game;
import com.example.game.main.Player;
import com.example.game.main.Region;

import static java.lang.Math.max;

public class CityCrew implements Unit{
    private Region position;
    private long Budget;
    private Player player;

    public CityCrew(Player player){
        this.Budget = Game.configuration.getInitBudget();
        this.player = player;
        this.moveToCityCenter();
    }

    @Override
    public Region getPosition() {
        return position;
    }

    @Override
    public boolean move(Direction d) {
        Budget -= 1;
        checkBudget();
        if (Budget < 1 ) return false;
        else {
            return updatePosition(position.getNeighbor(d));
        }
    }

    private boolean updatePosition(Region newPos){
        if(newPos != null) {
            if(newPos.getUnit()==null) {
                this.position = newPos;
                newPos.setUnit(this);
                return true;
            }
            return false;
        }return true;
    }

    public void moveToCityCenter(){
        position = player.getCityCenter().getPosition();
    }

    public long getBudget(){
        return Budget;
    }

    public boolean relocate(Region destination){
        Budget -= 1;
        checkBudget();
        int val = 5 * position.findShortestPath(destination).size() + 10;
        if(Budget < val ) return false;
        else {
            player.relocate(destination);
            return true;
        }
    }

    public boolean shoot(Direction direction,long value){
        Budget -= 1;
        checkBudget();
        if(Budget < value) return false;
        else {
            if (position.getNeighbor(direction) != null){
                Region r = position.getNeighbor(direction);
                Budget -= value;
                if(r.getLand() != null){
                    System.out.println("shoot");
                    r.getLand().decrease(value);
                }
            }
            return true;
        }
    }

    public void invest(long value){
        Budget -= 1;
        checkBudget();
        if (Budget < value) return;
        System.out.println(position.getX()+" "+position.getY());
        if(position.getLand() == null && position.isAdjacent(player)){
            Land land = player.createLand(position);
            land.increase(value);
        }else {
            position.getLand().increase(value);
        }
    }

    public boolean collect(long value){
        Budget -= 1;
        checkBudget();
        if (position.getLand() != null && position.getLand().collect(value)){
            Budget += value;
            return true;
        } else {
            return false;
        }
    }

    private void checkBudget(){
        this.Budget = max(Budget,0);
    }
}
