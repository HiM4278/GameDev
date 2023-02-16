package game.object;

public interface Realty extends GameObject{
    int getDeposit();
    boolean collect(int money);
    boolean invest(int budget);
}
