package game.main;

import extra.Direction;
import game.object.Realty;
import game.object.Unit;

import java.util.Map;

public class Region {
    private int x;
    private int y;
    private Realty realty;
    private Unit unit;
    private Map<Direction,Region> neighbor;
    public Region(int x,int y){
        this.x = x;
        this.y = y;
    }
    public void setNeighbor(Direction d , Region r) {
        neighbor.put(d,r);
    }
    public Region getNeighbor(Direction d){
        return neighbor.get(d);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Realty getRealty(){
        return realty;
    }
    public Unit getUnit(){
        return unit;
    }
}