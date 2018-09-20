package capstone.pong.view;

import capstone.pong.state.GameStats;

import javax.swing.*;
import java.awt.*;

import static capstone.pong.utils.Constants.*;

public final class GameUIComponents {
  private final JTextField gameStatsUI;
  private final GameBoardPanel gameBoardUI;

  private GameUIComponents(GameStats gameStats) {
    gameStatsUI = new JTextField(gameStats.currentRound().score.toString());
    gameStatsUI.setEnabled(false);

    gameBoardUI = GameBoardPanel.create();
    gameBoardUI.setSize(W, H);
    gameBoardUI.setBorder(BorderFactory.createLineBorder(Color.GRAY, THICKNESS));
    gameBoardUI.setFocusable(true);
    gameBoardUI.setFocusTraversalKeysEnabled(false);
  }

  public static GameUIComponents create(GameStats gameStats) {
    return new GameUIComponents(gameStats);
  }

  public JTextField getGameStatsUI() {
    return gameStatsUI;
  }

  public GameBoardPanel getGameBoardUI() {
    return gameBoardUI;
  }
}
