package coinclicker.ui;

import coinclicker.model.GameListener;
import coinclicker.model.GameState;
import coinclicker.model.Upgrade;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class ShopPanel extends JPanel implements GameListener {
    private final List<Runnable> rowUpdaters = new ArrayList<>();

    public ShopPanel(GameState state, List<Upgrade> catalog) {
        setLayout(new GridLayout(0, 1, 4, 4));
        setBorder(BorderFactory.createTitledBorder("Shop"));

        for (Upgrade upgrade : catalog) {
            JLabel info = new JLabel();
            JButton buy = new JButton("Buy");
            buy.addActionListener(e -> state.buy(upgrade));

            JPanel row = new JPanel(new BorderLayout(8, 0));
            row.add(info, BorderLayout.CENTER);
            row.add(buy, BorderLayout.EAST);
            add(row);

            rowUpdaters.add(() -> {
                info.setText(upgrade.getName() + " (Lv " + upgrade.getLevel()
                        + ") - " + upgrade.currentCost() + " coins");
                buy.setEnabled(state.canAfford(upgrade));
            });
        }

        state.addListener(this);
        refresh();
    }

    private void refresh() { rowUpdaters.forEach(Runnable::run); }

    @Override public void onCoinsChanged(long coins)    { refresh(); }
    @Override public void onUpgradePurchased(Upgrade u) { refresh(); }
}
