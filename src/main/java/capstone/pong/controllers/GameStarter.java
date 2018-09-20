package capstone.pong.controllers;

import capstone.pong.state.Ball;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class GameStarter
    extends AbstractAction {
  private final Ball ball;
  private final Timer timer;

  private GameStarter(
      String actionName,
      Timer timer,
      Ball ball
  ) {
    super(actionName);
    this.ball = ball;
    this.timer = timer;
    putValue(MNEMONIC_KEY, KeyEvent.VK_SPACE);
  }

  public static GameStarter startGame(
      Timer timer,
      Ball ball
  ) {
    return new GameStarter("start newGameController", timer, ball);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    timer.start();
    ball.start();
  }
}
