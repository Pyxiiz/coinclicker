package coinclicker.ui;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class GameWindow extends JFrame {

    public GameWindow(JComponent center, JComponent east) {
        super("Coin Clicker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        add(center, BorderLayout.CENTER);
        add(east, BorderLayout.EAST);
        pack();
        setLocationRelativeTo(null);
    }
}
