package com.example.game.object;

public interface Realty extends GameObject{
    int getDeposit();
    boolean decrease(long money);
    void increase(long budget);
}
