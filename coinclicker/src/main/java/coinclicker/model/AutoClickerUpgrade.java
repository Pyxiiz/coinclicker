package coinclicker.model;

import coinclicker.cost.CostStrategy;

public class AutoClickerUpgrade extends Upgrade {
    public AutoClickerUpgrade(CostStrategy c) { super("Auto Miner", 50, c); }
    @Override protected void applyEffect(GameState s) { s.addCoinsPerSecond(1); }
}
