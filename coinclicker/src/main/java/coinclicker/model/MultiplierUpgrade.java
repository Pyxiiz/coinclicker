package coinclicker.model;

import coinclicker.cost.CostStrategy;

public class MultiplierUpgrade extends Upgrade {
    public MultiplierUpgrade(CostStrategy c) { super("Golden Touch", 500, c); }
    @Override protected void applyEffect(GameState s) { s.multiplyBy(1.5); }
}
