package coinclicker.cost;

public interface CostStrategy {
    long costFor(long baseCost, int level);
}
