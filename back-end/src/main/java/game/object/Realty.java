package game.object;

public interface Realty extends GameObject{
    int getDeposit();
    boolean increase(long money);
    void decrease(long budget);
}
