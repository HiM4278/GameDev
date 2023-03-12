package game.object;

import game.main.Player;
import game.main.Region;


public class Land implements Realty{
    private Region position;
    private boolean isCityCenter;
    private Player Owner;
    private long maxDep;
    private double deposit;
    public Land(long maxDeposit,Player owner,Region region){
        this.maxDep = maxDeposit;
        this.deposit = 0;
        this.Owner = owner;
        this.position = region;
        if (owner != null){
            this.isCityCenter = true;
            this.deposit = maxDeposit;
        } else {
            this.isCityCenter = false;
        }
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
        } else {
           double d =  deposit -= money;
           deposit = d;
            if(d < 0){
                deposit = 0;
                Owner = null; // if Region's deposit == 0, The region will have no owner.
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
        return Owner;
    }
    public void setThisToCenter(){
        this.isCityCenter = true;
    }
    public void updateDeposit(double interest){
        deposit += deposit*interest/100;
    }
    public boolean isCityCenters () { return this.isCityCenter; }


}
