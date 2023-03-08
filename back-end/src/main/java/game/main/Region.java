package game.main;

import extra.Direction;
import game.object.Realty;
import game.object.Unit;

import java.util.HashMap;
import java.util.Map;

public class Region {
    private int x;
    private int y;
    private Realty realty;
    private Unit unit;
    private Map<Direction,Region> neighbor = new HashMap<>();
    public Region(int x,int y){
        this.x = x;
        this.y = y;
    }
    public void setNeighbor(Direction d , Region r) {
        this.neighbor.put(d,r);
    }
    public Region getNeighbor(Direction d){
        return this.neighbor.get(d);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setRealty(Realty r){
        this.realty = r;
    }
    public void setUnit(Unit u){
        this.unit = u;
    }
    public Realty getRealty(){
        return realty;
    }
    public Unit getUnit(){
        return unit;
    }
}