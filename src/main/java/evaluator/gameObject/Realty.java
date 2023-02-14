package evaluator.gameObject;

public interface Realty extends GameObject{
    int getDeposit();
    boolean collect(int money);
    boolean invest(int budget);
}
