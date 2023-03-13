package game.object;

import game.main.Game;
import game.main.Player;
import game.main.Region;


public class Land implements Realty{
    private Region position;
    private boolean isCityCenter;
    private Player owner;
    private final long maxDep;
    private double deposit;

    public Land(Player owner, Region region){
        this.maxDep = Game.configuration.getMax_dep();
        this.deposit = 0;
        this.owner = owner;
        this.position = region;
    }

    public Land(Player owner, Region region, boolean isCityCenter){
        this(owner, region);
        this.isCityCenter = true;
        this.deposit = Game.configuration.getInitCenterDeposit();
    }

    @Override
    public Region getPosition() {
        return position;
    }

    @Override
    public int getDeposit() {
        return (int) deposit;
    }

    @Override
    public boolean decrease(long money) {
        if (deposit < money){
            return false;
        } else if(deposit == money) {
            owner.soldLand(this);
            return true;
        } else {
           double d =  deposit -= money;
           deposit = d;
            if(d < 0){
                deposit = 0;
                owner = null; // if Region's deposit == 0, The region will have no owner.
            }
            return true;
        }
    }

    @Override
    public void increase(long budget) {
        double d = deposit + budget;
        deposit = d;
        if (d > maxDep){
            deposit = maxDep;
        }
    }
    public Player getOwner(){
        return owner;
    }

    public void setToCenter(){
        this.isCityCenter = true;
    }

    public void setToNormalLand(){
        this.isCityCenter = false;
    }

    public void updateDeposit(double interest){
        deposit += deposit*interest/100;
    }

    public boolean isCityCenters () { return this.isCityCenter; }


}
