package coinclicker.factory;

import coinclicker.cost.LinearCost;
import coinclicker.model.AutoClickerUpgrade;
import coinclicker.model.ClickPowerUpgrade;
import coinclicker.model.Upgrade;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RegistryUpgradeFactoryTest {

    private RegistryUpgradeFactory newFactory() {
        RegistryUpgradeFactory f = new RegistryUpgradeFactory();
        f.register("click", () -> new ClickPowerUpgrade(new LinearCost()));
        f.register("auto",  () -> new AutoClickerUpgrade(new LinearCost()));
        return f;
    }

    @Test void createBuildsTheRegisteredUpgrade() {
        assertInstanceOf(ClickPowerUpgrade.class, newFactory().create("click"));
    }

    @Test void createReturnsAFreshInstanceEachTime() {
        RegistryUpgradeFactory f = newFactory();
        assertNotSame(f.create("click"), f.create("click"));
    }

    @Test void unknownIdThrows() {
        assertThrows(IllegalArgumentException.class, () -> newFactory().create("nope"));
    }

    @Test void createAllKeepsRegistrationOrder() {
        List<Upgrade> all = newFactory().createAll();
        assertEquals(2, all.size());
        assertInstanceOf(ClickPowerUpgrade.class, all.get(0));
        assertInstanceOf(AutoClickerUpgrade.class, all.get(1));
    }
}
