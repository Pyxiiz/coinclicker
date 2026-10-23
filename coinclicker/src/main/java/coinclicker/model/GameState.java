package coinclicker.model;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private final Wallet wallet;                       // injected
    private final List<GameListener> listeners = new ArrayList<>();
    private long coinsPerClick = 1;
    private long coinsPerSecond = 0;
    private double multiplier = 1.0;

    public GameState(Wallet wallet) {
        this.wallet = wallet;
    }

    public void addListener(GameListener l)    { listeners.add(l); }
    public void removeListener(GameListener l) { listeners.remove(l); }

    public void click() {
        wallet.add(Math.round(coinsPerClick * multiplier));
        notifyCoinsChanged();
    }

    public void tick() {
        wallet.add(Math.round(coinsPerSecond * multiplier));
        notifyCoinsChanged();
    }

    public boolean canAfford(Upgrade upgrade) {
        return wallet.getBalance() >= upgrade.currentCost();
    }

    public boolean buy(Upgrade upgrade) {
        if (!upgrade.purchase(this)) return false;
        notifyCoinsChanged();
        listeners.forEach(l -> l.onUpgradePurchased(upgrade));
        return true;
    }

    private void notifyCoinsChanged() {
        long balance = wallet.getBalance();
        listeners.forEach(l -> l.onCoinsChanged(balance));
    }

    public Wallet getWallet()             { return wallet; }
    public void addCoinsPerClick(long n)  { coinsPerClick += n; }
    public void addCoinsPerSecond(long n) { coinsPerSecond += n; }
    public void multiplyBy(double m)      { multiplier *= m; }
    public long getCoinsPerClick()        { return coinsPerClick; }
    public long getCoinsPerSecond()       { return coinsPerSecond; }
    public double getMultiplier()         { return multiplier; }
}
