package coinclicker.model;

public interface GameListener {
    void onCoinsChanged(long coins);
    void onUpgradePurchased(Upgrade upgrade);
}
