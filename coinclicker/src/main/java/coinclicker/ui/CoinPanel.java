package coinclicker.ui;

import coinclicker.model.GameListener;
import coinclicker.model.GameState;
import coinclicker.model.Upgrade;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CoinPanel extends JPanel implements GameListener {
    private final JLabel coinLabel = new JLabel("Coins: 0");

    public CoinPanel(GameState state) {
        JButton coinButton = new JButton("CLICK");
        coinButton.addActionListener(e -> state.click());
        add(coinLabel);
        add(coinButton);
        state.addListener(this);   // observer registration
    }

    @Override public void onCoinsChanged(long coins)    { coinLabel.setText("Coins: " + coins); }
    @Override public void onUpgradePurchased(Upgrade u) { }
}
