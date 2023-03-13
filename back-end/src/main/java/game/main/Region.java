package game.main;

import extra.Direction;
import game.object.Land;
import game.object.Realty;
import game.object.Unit;

import java.util.*;

public class Region {
    private int x;
    private int y;
    private Land land;
    private Unit unit;
    private final Map<Direction,Region> neighbors = new HashMap<>();
    public Region(int x,int y){
        this.x = x;
        this.y = y;
    }
    public void setNeighbor(Direction d , Region r) {
        this.neighbors.put(d,r);
    }
    public Region getNeighbor(Direction d){
        return this.neighbors.get(d);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setLand(Land r){
        this.land = r;
    }
    public void setUnit(Unit u){
        this.unit = u;
    }
    public boolean isAdjacent(Player player){
        for(Region neighbor : neighbors.values().toArray(new Region[0])){
            if(neighbor.getLand() != null && neighbor.getLand().getOwner() == player) return true;
        }
        return false;
    }

    public Land getLand(){
        return land;
    }
    public Unit getUnit(){
        return unit;
    }
    public List<Region> findShortestPath(Region destination) {
        Queue<Region> queue = new LinkedList<>();
        Map<Region, Region> parentMap = new HashMap<>(); // store parent region for each visited region
        Set<Region> visited = new HashSet<>();

        queue.offer(this);
        visited.add(this);

        while (!queue.isEmpty()) {
            Region currentRegion = queue.poll();
            if (currentRegion == destination) {
                break; // found destination region, exit loop
            }
            for (Direction direction : Direction.values()) {
                Region neighbor = currentRegion.getNeighbor(direction);
                if (neighbor != null && !visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, currentRegion);
                }
            }
        }

        // build path from parentMap
        List<Region> path = new ArrayList<>();
        Region currentRegion = destination;
        while (currentRegion != null) {
            path.add(currentRegion);
            currentRegion = parentMap.get(currentRegion);
        }
        Collections.reverse(path);

        return path;
    }


}