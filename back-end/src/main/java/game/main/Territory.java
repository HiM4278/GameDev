package game.main;

import extra.Direction;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Territory {
    private Set<Region> UsedRegion = new HashSet<>();
    private Region[][] region;
    private Random random = new Random();
    private int m,n;



    public Territory(int m,int n){
        this.m = m;
        this.n = n;
        region = new Region[m][n];
    }

    public void printRegion(){
        for(int i = 0; i < m; i++) {
            if(i%2 == 0) System.out.print("      ");
            for(int j = 0; j < n; j++) {
                System.out.print("[    ]      ");
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
//                if(j == 1){
//                    regions[i][j].setNeighbor(Direction.UPLEFT,null);
//                    regions[i][j].setNeighbor(Direction.DOWNLEFT,null);
//                }
//                if(i == 1){
//                    if (j%2 == 0){
//                        regions[i][j].setNeighbor(Direction.UPLEFT,null);
//                        regions[i][j].setNeighbor(Direction.UPRIGHT,null);
//                    }
//                    regions[i][j].setNeighbor(Direction.UP,null);
//                }
//                if(j == n){
//                    regions[i][j].setNeighbor(Direction.UPRIGHT,null);
//                    regions[i][j].setNeighbor(Direction.DOWNRIGHT,null);
//                }
//                if(i == m){
//                    if(j%2 == 1){
//                        regions[i][j].setNeighbor(Direction.DOWNLEFT,null);
//                        regions[i][j].setNeighbor(Direction.DOWNRIGHT,null);
//                    }
//                    regions[i][j].setNeighbor(Direction.DOWN,null);
//                }
            }
        }
    }
    public Region RandomRegion(){
        int x,y;
        Region r;
        do {
            x = random.nextInt(m)+1;
            y = random.nextInt(n)+1;
            r = region[x][y];
        }while (UsedRegion.contains(r));
        UsedRegion.add(r);
        return r;

    }
}
