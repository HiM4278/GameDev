package com.example.message;

import com.example.game.main.Player;
import com.example.game.main.Region;
import com.example.game.main.Territory;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RegionMessage {
    private boolean isCityCenter;
    private boolean isVisible;
    private UUID ownerID;
    private String color;
    private int tileID;
    private boolean isEmpty;
    private int deposit;

    public RegionMessage(Region region){
        if(region.getLand() != null){
            this.isCityCenter = region.getLand().isCityCenters();
            this.ownerID = region.getLand().getOwner().getId();
            this.isEmpty = false;
            this.color = region.getLand().getOwner().getColor();
            this.deposit = region.getLand().getDeposit();
        }else {
            this.isCityCenter = false;
            this.ownerID = UUID.randomUUID();
            this.deposit = 0;
            this.color = "";
            this.isEmpty = true;
        }
        this.tileID = region.getTileID();
        this.isVisible = true;
    }
}
