package com.example.game.main;

import com.example.extra.Direction;
import com.example.game.object.Land;
import com.example.game.object.Unit;
import lombok.Getter;

import java.util.*;

public class Region {
    private int x;
    private int y;
    private Land land;
    private Unit unit;
    private int tileID;
    private final Map<Direction,Region> neighbors = new HashMap<>();
    private static ArrayList<Double> prob = new ArrayList<>(Arrays.asList(1/2.0, 1/4.0, 1/8.0, 1/8.0));

    public Region(int x,int y){
        this.x = x;
        this.y = y;
        this.tileID = randomTile();
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
    public int getTileID() {
        return tileID;
    }
    public void setLand(Land r){
        this.land = r;
    }
    public void setUnit(Unit u){
        this.unit = u;
    }
    public boolean isAdjacent(Player player){
        for(Region neighbor : neighbors.values().toArray(new Region[0])){
            if(neighbor != null) {
                if(neighbor.getLand()!=null && neighbor.getLand().getOwner()==player) return true;
            }
        }
        return false;
    }

    public Land getLand(){
        return this.land;
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

    public static int randomTile(){
        double rand = Math.random();
        double sum = 0;
        for(int i = 0; i < prob.size(); i++) {
            if(rand < sum){
                return i;
            }
            sum += prob.get(i);
        }
        return prob.size();
    }
}