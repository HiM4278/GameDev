package game.object;

import extra.Direction;
import game.main.Region;

public class CityCrew implements Unit{
    private Region position;
    private int Budget;
    private Land base;
    public CityCrew(int Budget,Land base,Region position){
        this.Budget = Budget;
        this.position = position;
        this.base = base;
    }
    @Override
    public Region getPosition() {
        return position;
    }

    @Override
    public boolean move(Direction d) {
        if (Budget < 1 ) return false;
        else {
            Budget -= 1;
            return true;
        }
    }
    public long getBudget(){
        return Budget;
    }
    public boolean relocate(){
        int val = 5 * position.getX() + 10;
        if(Budget >= val ) return false;
        return true;
    }
    public boolean shoot(Direction direction,long value){
        Budget -= 1;
        if(Budget < value) return false;
        else {
            Budget -= value;
            return true;
        }
    }
    public void invest(long value){

    }
}
