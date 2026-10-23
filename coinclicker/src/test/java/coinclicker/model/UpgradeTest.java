package coinclicker.model;

import coinclicker.cost.CostStrategy;
import coinclicker.cost.LinearCost;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UpgradeTest {

    private GameState newState() { return new GameState(new CoinWallet()); }

    @Test void purchaseDeductsCostAndAppliesEffect() {
        GameState s = newState();
        s.getWallet().add(10);
        Upgrade u = new ClickPowerUpgrade(new LinearCost());
        assertTrue(u.purchase(s));
        assertEquals(2, s.getCoinsPerClick());
        assertEquals(0, s.getWallet().getBalance());
    }

    @Test void purchaseFailsWithoutEnoughCoinsAndChangesNothing() {
        GameState s = newState();
        Upgrade u = new AutoClickerUpgrade(new LinearCost());
        assertFalse(u.purchase(s));
        assertEquals(0, s.getCoinsPerSecond());
        assertEquals(0, u.getLevel());
    }

    @Test void costRisesAfterEachPurchase() {
        GameState s = newState();
        s.getWallet().add(100);
        Upgrade u = new ClickPowerUpgrade(new LinearCost());
        assertEquals(10, u.currentCost());
        u.purchase(s);
        assertEquals(20, u.currentCost());
        assertEquals(1, u.getLevel());
    }

    @Test void injectedCostStrategyDecidesThePrice() {
        CostStrategy flat = (base, level) -> 7;     // stub strategy
        Upgrade u = new ClickPowerUpgrade(flat);
        assertEquals(7, u.currentCost());
    }

    @Test void eachSubclassAppliesItsOwnEffect() {   // polymorphism
        GameState s = newState();
        s.getWallet().add(1000);
        CostStrategy c = new LinearCost();
        List<Upgrade> all = List.of(
                new ClickPowerUpgrade(c),
                new AutoClickerUpgrade(c),
                new MultiplierUpgrade(c));
        all.forEach(u -> u.purchase(s));
        assertEquals(2, s.getCoinsPerClick());
        assertEquals(1, s.getCoinsPerSecond());
        assertEquals(1.5, s.getMultiplier(), 0.0001);
    }
}
