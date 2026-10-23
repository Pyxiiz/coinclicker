package coinclicker.model;

import coinclicker.cost.CostStrategy;

public abstract class Upgrade {
    private final String name;
    private final long baseCost;
    private final CostStrategy costStrategy;   // injected
    private int level;

    protected Upgrade(String name, long baseCost, CostStrategy costStrategy) {
        this.name = name;
        this.baseCost = baseCost;
        this.costStrategy = costStrategy;
    }

    // Template Method: the order of steps is fixed, subclasses only supply applyEffect
    public final boolean purchase(GameState state) {
        if (!state.getWallet().trySpend(currentCost())) return false;
        level++;
        applyEffect(state);
        return true;
    }

    protected abstract void applyEffect(GameState state);

    public long currentCost() { return costStrategy.costFor(baseCost, level); }
    public String getName()   { return name; }
    public int getLevel()     { return level; }
}
