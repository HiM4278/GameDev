package game.main;

import extra.Direction;

public class Territory {
    private Region[][] region;
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

    public void createAllRegion(){
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                region[i][j] = new Region(i,j);
//                if(i - 1 == 0 || j - 1 == 0 || )
                region[i][j].setNeighbor(Direction.UP,region[i-1][j]);
                region[i][j].setNeighbor(Direction.DOWN,region[i+1][j]);
                region[i][j].setNeighbor(Direction.UPLEFT,region[i-1][j-1]);
                region[i][j].setNeighbor(Direction.UPRIGHT,region[i-1][j+1]);
                region[i][j].setNeighbor(Direction.DOWNLEFT,region[i][j-1]);
                region[i][j].setNeighbor(Direction.DOWNRIGHT,region[i][j+1]);
            }
        }
    }
}
