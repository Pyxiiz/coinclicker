package coinclicker.cost;

public class ExponentialCost implements CostStrategy {
    private final double growth;

    public ExponentialCost(double growth) { this.growth = growth; }

    @Override
    public long costFor(long base, int level) {
        return Math.round(base * Math.pow(growth, level));
    }
}
