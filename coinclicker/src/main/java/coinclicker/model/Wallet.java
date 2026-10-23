package coinclicker.model;

public interface Wallet {
    void add(long amount);
    boolean trySpend(long amount);
    long getBalance();
}
