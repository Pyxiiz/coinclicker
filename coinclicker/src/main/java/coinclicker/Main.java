package coinclicker;

import coinclicker.cost.ExponentialCost;
import coinclicker.cost.LinearCost;
import coinclicker.factory.RegistryUpgradeFactory;
import coinclicker.model.AutoClickerUpgrade;
import coinclicker.model.ClickPowerUpgrade;
import coinclicker.model.CoinWallet;
import coinclicker.model.GameState;
import coinclicker.model.MultiplierUpgrade;
import coinclicker.ui.CoinPanel;
import coinclicker.ui.GameLoop;
import coinclicker.ui.GameWindow;
import coinclicker.ui.ShopPanel;
import coinclicker.ui.SwingGameLoop;

import javax.swing.SwingUtilities;

/** Composition root: the only place concrete classes are instantiated and wired together. */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::start);
    }

    private static void start() {
        RegistryUpgradeFactory factory = new RegistryUpgradeFactory();
        factory.register("click-power",  () -> new ClickPowerUpgrade(new LinearCost()));
        factory.register("auto-miner",   () -> new AutoClickerUpgrade(new ExponentialCost(1.15)));
        factory.register("golden-touch", () -> new MultiplierUpgrade(new ExponentialCost(2.0)));

        GameState state = new GameState(new CoinWallet());
        GameLoop loop = new SwingGameLoop(1000, state::tick);

        GameWindow window = new GameWindow(
                new CoinPanel(state),
                new ShopPanel(state, factory.createAll()));

        loop.start();
        window.setVisible(true);
    }
}
