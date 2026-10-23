package coinclicker.ui;

import javax.swing.Timer;

public class SwingGameLoop implements GameLoop {
    private final Timer timer;

    public SwingGameLoop(int periodMs, Runnable onTick) {
        this.timer = new Timer(periodMs, e -> onTick.run());
    }

    @Override public void start() { timer.start(); }
    @Override public void stop()  { timer.stop(); }
}
