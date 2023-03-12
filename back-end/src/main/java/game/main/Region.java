package game.main;

import extra.Direction;
import game.object.Realty;
import game.object.Unit;

import java.util.*;

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