package capstone.pong.view;

import capstone.pong.state.Pad;

import javax.swing.*;
import java.awt.*;

import static capstone.pong.utils.Constants.*;

public final class GameBoard {

  private final GameUIComponents components;
  private final Timer timer;

  private GameBoard(
      GameUIComponents components,
      Timer timer
  ) {
    this.components = components;
    this.timer = timer;
  }

  public void go() {
    JFrame jFrame = new JFrame("Ping-Pong") {
      @Override
      public void paint(Graphics g) {
        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        gg.setRenderingHint(
            RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY);
        super.paint(gg);
      }
    };
    jFrame.setLayout(new BorderLayout());
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setLocationRelativeTo(null);

    JComponent mainPanel = mainPanel();
    jFrame.setSize(mainPanel.getWidth(), mainPanel.getHeight());
    jFrame.getContentPane().add(mainPanel);

    jFrame.setVisible(true);
  }

  public static GameBoard create(
      GameUIComponents components,
      Timer timer
  ) {
    return new GameBoard(components, timer);
  }

  private JComponent mainPanel() {
    JComponent mainPanel = CapstoneJPanel.create();
    JComponent gameStatsPanel = CapstoneJPanel.create();
    gameStatsPanel.add(components.getGameStatsUI());
    gameStatsPanel.setSize(W, THICKNESS);

    mainPanel.setLayout(new BorderLayout());
    mainPanel.setSize(W, H + Pad.Heigth + THICKNESS + gameStatsPanel.getHeight() - 1);
    mainPanel.add(gameStatsPanel, BorderLayout.NORTH);
    mainPanel.add(components.getGameBoardUI(), BorderLayout.CENTER);

    return mainPanel;
  }
}
