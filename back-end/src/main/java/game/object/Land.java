package game.object;

import game.main.Player;
import game.main.Region;

public class Land implements Realty{
    protected Region position;
    private Player Owner;
    protected double deposit;

    @Override
    public Region getPosition() {
        return null;
    }

    @Override
    public int getDeposit() {
        return 0;
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
