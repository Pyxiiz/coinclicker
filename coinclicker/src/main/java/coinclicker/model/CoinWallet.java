package coinclicker.model;

public class CoinWallet implements Wallet {
    private long balance;

    @Override
    public void add(long amount) {
        if (amount < 0) throw new IllegalArgumentException("amount must be >= 0");
        balance += amount;
    }

    @Override
    public boolean trySpend(long amount) {
        if (amount < 0 || amount > balance) return false;
        balance -= amount;
        return true;
    }

    @Override
    public long getBalance() { return balance; }
}
