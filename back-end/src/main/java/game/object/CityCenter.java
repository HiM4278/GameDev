package game.object;

import game.main.Player;
import game.main.Region;

public class CityCenter extends Land{


    public CityCenter(double deposit, Player owner, Region region) {
        super(deposit, owner, region);
    }

    private boolean move(Region r){
        return true;
    }
}
