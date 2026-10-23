package coinclicker.model;

import coinclicker.cost.LinearCost;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    private GameState newState() { return new GameState(new CoinWallet()); }

    @Test void clickAddsCoins() {
        GameState s = newState();
        s.click();
        assertEquals(1, s.getWallet().getBalance());
    }

    @Test void tickGivesPassiveIncome() {
        GameState s = newState();
        s.addCoinsPerSecond(3);
        s.tick();
        assertEquals(3, s.getWallet().getBalance());
    }

    @Test void multiplierAffectsClickIncome() {
        GameState s = newState();
        s.multiplyBy(2.0);
        s.click();
        assertEquals(2, s.getWallet().getBalance());
    }

    @Test void listenerIsNotifiedOnClick() {
        GameState s = newState();
        long[] seen = {-1};
        s.addListener(new GameListener() {
            public void onCoinsChanged(long c) { seen[0] = c; }
            public void onUpgradePurchased(Upgrade u) { }
        });
        s.click();
        assertEquals(1, seen[0]);
    }

    @Test void removedListenerIsNotNotified() {
        GameState s = newState();
        boolean[] called = {false};
        GameListener l = new GameListener() {
            public void onCoinsChanged(long c) { called[0] = true; }
            public void onUpgradePurchased(Upgrade u) { }
        };
        s.addListener(l);
        s.removeListener(l);
        s.click();
        assertFalse(called[0]);
    }

    @Test void buyNotifiesCoinsBeforePurchase() {
        GameState s = newState();
        s.getWallet().add(10);
        List<String> events = new ArrayList<>();
        s.addListener(new GameListener() {
            public void onCoinsChanged(long c) { events.add("coins"); }
            public void onUpgradePurchased(Upgrade u) { events.add("purchased"); }
        });
        s.buy(new ClickPowerUpgrade(new LinearCost()));
        assertEquals(List.of("coins", "purchased"), events);
    }

    @Test void canAffordReflectsBalance() {
        GameState s = newState();
        Upgrade u = new ClickPowerUpgrade(new LinearCost());
        assertFalse(s.canAfford(u));
        s.getWallet().add(10);
        assertTrue(s.canAfford(u));
    }
}
