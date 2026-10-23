package coinclicker.cost;

public class LinearCost implements CostStrategy {
    @Override
    public long costFor(long base, int level) { return base * (level + 1); }
}
