package com.example.game.main;

import com.example.extra.Direction;

import java.util.*;

public class Territory {
    private Set<Region> UsedRegion = new HashSet<>();
    private Random random = new Random();
    private int m,n;
    private Region[][] regions;

    public Territory(int m,int n){
        this.m = m;
        this.n = n;
        regions = new Region[m+2][n+2];
        createAllRegion();
    }

    public void printRegion(){
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                System.out.print(regions[i][j] +" ");
            }
            System.out.println();
        }
    }

    private void createAllRegion(){
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++) {
                regions[i][j] = new Region(i, j);
            }
        }

        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                regions[i][j].setNeighbor(Direction.UP,regions[i-1][j]);
                regions[i][j].setNeighbor(Direction.UPLEFT,regions[i-1][j-1]);
                regions[i][j].setNeighbor(Direction.UPRIGHT,regions[i-1][j+1]);
                regions[i][j].setNeighbor(Direction.DOWN,regions[i+1][j]);
                regions[i][j].setNeighbor(Direction.DOWNLEFT,regions[i][j-1]);
                regions[i][j].setNeighbor(Direction.DOWNRIGHT,regions[i][j+1]);
                if(j%2 != 0) {
                    regions[i][j].setNeighbor(Direction.UPRIGHT,regions[i][j+1]);
                    regions[i][j].setNeighbor(Direction.UPLEFT,regions[i][j-1]);
                    regions[i][j].setNeighbor(Direction.DOWNRIGHT,regions[i+1][j+1]);
                    regions[i][j].setNeighbor(Direction.DOWNLEFT,regions[i+1][j-1]);
                }
            }
        }
    }

    public Region RandomRegion(){
        int x,y;
        Region r;
        do {
            x = random.nextInt(m)+1;
            y = random.nextInt(n)+1;
            r = regions[x][y];
        }while (UsedRegion.contains(r));
        UsedRegion.add(r);
        return r;

    }
    public Region getRegions(int x ,int y){
        return regions[x][y];
    }
}
