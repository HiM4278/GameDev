package game.object;

import extra.Direction;
import game.main.Region;

public class CityCrew implements Unit{
    private Region position;
    private CityCenter base;
    private int Budget;
    public CityCrew(int Budget){
        this.Budget = Budget;
        moveToCityCenter();
    }
    @Override
    public Region getPosition() {
        return position;
    }

    @Override
    public boolean move(Direction d) {
        return false;
    }
    public int getBudget(){
        return Budget;
    }
    public void moveToCityCenter(){
        this.position = base.position;
    }
}
