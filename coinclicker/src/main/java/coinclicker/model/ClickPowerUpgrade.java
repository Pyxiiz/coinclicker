package coinclicker.model;

import coinclicker.cost.CostStrategy;

public class ClickPowerUpgrade extends Upgrade {
    public ClickPowerUpgrade(CostStrategy c) { super("Stronger Fingers", 10, c); }
    @Override protected void applyEffect(GameState s) { s.addCoinsPerClick(1); }
}
