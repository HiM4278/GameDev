package game.main;

public class Territory {
    Region[][] region;
    int m,n;

    public Territory(){
        m=20;
        n=10;
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

    public void createRegion(){

    }
}
