package coinclicker.cost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CostStrategyTest {

    @Test void linearCostScalesWithLevel() {
        CostStrategy c = new LinearCost();
        assertEquals(10, c.costFor(10, 0));
        assertEquals(30, c.costFor(10, 2));
    }

    @Test void exponentialCostGrowsPerLevel() {
        CostStrategy c = new ExponentialCost(2.0);
        assertEquals(10, c.costFor(10, 0));
        assertEquals(40, c.costFor(10, 2));
    }

    @Test void exponentialIsNeverCheaperThanLinearAtHighLevels() {
        CostStrategy linear = new LinearCost();
        CostStrategy expo = new ExponentialCost(1.5);
        assertTrue(expo.costFor(10, 10) > linear.costFor(10, 10));
    }
}
