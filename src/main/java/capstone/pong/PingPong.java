package capstone.pong;

import capstone.pong.controllers.*;
import capstone.pong.state.Ball;
import capstone.pong.state.GameStats;
import capstone.pong.state.Pad;
import capstone.pong.view.BallPainter;
import capstone.pong.view.GameBoard;
import capstone.pong.view.GameUIComponents;
import capstone.pong.view.PadPainter;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class PingPong {
  private static final int topPlayerLeft = KeyEvent.VK_A;
  private static final int topPlayerRight = KeyEvent.VK_D;
  private static final int bottomPlayerLeft = KeyEvent.VK_J;
  private static final int bottomPlayerRight = KeyEvent.VK_L;
  private static final int space = KeyEvent.VK_SPACE;
  private static final int delay = 10;

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      try {
        final Timer timer = new Timer(delay, null);
        final GameStats gameStats = GameStats.newGame();
        final GameUIComponents components = GameUIComponents.create(gameStats);
        final Pad topPad = Pad.topPad();
        final Pad bottomPad = Pad.bottomPad();
        final Ball ball = Ball.ball();
        final GameStatsController gameStatsController = GameStatsController.create(
            () -> components.getGameStatsUI().setText(gameStats.currentRound().score.toString())
        );
        timer.addActionListener(gameStatsController);

        final GameController gameBoardController = GameController.newGameController(
            () -> components.getGameBoardUI().repaint(),
            gameStats
        );
        timer.addActionListener(gameBoardController);

        components.getGameBoardUI().add(
            LeftPadController.leftPad(topPad, topPlayerLeft),
            topPlayerLeft,
            "paddle1-left"
        );
        components.getGameBoardUI().add(
            RightPadController.rightPad(topPad, topPlayerRight),
            topPlayerRight,
            "paddle1-right"
        );
        components.getGameBoardUI().add(
            LeftPadController.leftPad(bottomPad, bottomPlayerLeft),
            bottomPlayerLeft,
            "paddle2-left"
        );
        components.getGameBoardUI().add(
            RightPadController.rightPad(bottomPad, bottomPlayerRight),
            bottomPlayerRight,
            "paddle2-right"
        );
        components.getGameBoardUI().add(
            GameStarter.startGame(timer, ball),
            space,
            "ball-start"
        );

        components.getGameBoardUI().addPainter(PadPainter.create(topPad));
        components.getGameBoardUI().addPainter(PadPainter.create(bottomPad));
        components.getGameBoardUI().addPainter(BallPainter.create(ball));

        gameBoardController.registerMoveable(topPad);
        gameBoardController.registerMoveable(bottomPad);
        gameBoardController.registerMoveable(ball);

        final GameBoard gameBoard = GameBoard.create(components);
        gameBoard.go();
      } catch (Exception e) {
        e.printStackTrace(System.out);
      }
    });
  }
}
