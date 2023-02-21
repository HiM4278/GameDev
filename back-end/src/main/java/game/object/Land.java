package game.object;

import game.main.Player;
import game.main.Region;

public class Land implements Realty{
    protected Region position;
    private Player Owner;
    protected double deposit;
    public Land(double deposit,Player owner,Region region){
        this.deposit = deposit;
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
        return false;
    }

    @Override
    public boolean invest(int budget) {
        return false;
    }
}
