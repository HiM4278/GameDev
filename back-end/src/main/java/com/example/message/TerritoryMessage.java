package com.example.message;

import com.example.game.main.Match;
import com.example.game.main.Region;
import com.example.game.main.Territory;
import lombok.Getter;

import java.util.UUID;

@Getter
public class TerritoryMessage {
    private RegionMessage[][] regions;
    private int m,n;

    private UUID currentPlayer;

    public TerritoryMessage(Match match){
        Territory territory = match.getTerritory();
        this.m = territory.getM();
        this.n = territory.getN();
        this.regions = new RegionMessage[m][n];

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                regions[i-1][j-1] = new RegionMessage(territory.getRegions(i,j));
            }
            System.out.println();
        }
    }
}
