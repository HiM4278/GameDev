package game.object;

import extra.Direction;
import game.main.Region;

import static java.lang.Math.max;

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
        Budget -= 1;
        checkBudget();
        if (Budget < 1 ) return false;
        else {
            position.getUnit().move(d);
            return true;
        }
    }
    public long getBudget(){
        return Budget;
    }
    public boolean relocate(){
        Budget -= 1;
        checkBudget();
        int val = 5 * position.getX() + 10;
        if(Budget >= val ) return false;
        return true;
    }
    public boolean shoot(Direction direction,long value){
        Budget -= 1;
        checkBudget();
        if(Budget < value) return false;
        else {
            if (position.getNeighbor(direction) != null){
                Region r = position.getNeighbor(direction);
                Budget -= value;
                r.getRealty().increase(value);
            }
            return true;
        }
    }
    public void invest(long value){
        Budget -= 1;
        checkBudget();
        if (Budget < value){}
        else{ position.getRealty().decrease(value); }

    }
    public boolean collect(long value){
        Budget -= 1;
        checkBudget();
        if (position.getRealty().increase(value)){
            Budget += value;
            return true;
        } else {
            return false;
        }
    }

    private void checkBudget(){
        this.Budget = max(Budget,0);
    }
    public void SetBasePosition(){
        position = base.getPosition();
    }
}
