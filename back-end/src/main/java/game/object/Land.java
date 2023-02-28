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
        this.deposit = maxDeposit;
        this.Owner = owner;
        this.position = region;
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
    public boolean collect(int money) {
        if (deposit < money){
            return false;
        } else {
           double d =  deposit -= money;
           deposit = d;
            if(d < 0){
                deposit = 0;
            }
            return true;
        }
    }

    @Override
    public boolean invest(int budget) {
        double d = deposit + budget;
        if (d > maxDep){
            deposit = maxDep;
        }
        return false;
    }
    public Player getOwner(){
        return Owner;
    }
    public void setThisToCenter(){

    }
}
