package capstone.pong.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class GameStatsController
        implements ActionListener {
    private Runnable r;

    private GameStatsController(Runnable r) {
        this.r = r;
    }

    public static GameStatsController create(Runnable r) {
        return new GameStatsController(r);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        r.run();
    }
}
