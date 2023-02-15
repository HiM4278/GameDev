package game.object;

import game.main.Region;

public class CityCrew implements Unit{
    private Region position;
    private CityCenter base;
    private int Budget;

    @Override
    public Region getPosition() {
        return null;
    }

    @Override
    public boolean move(int x) {
        return false;
    }
}
