package capstone.pong.controllers;

import capstone.pong.state.Ball;
import capstone.pong.state.GameStats;
import capstone.pong.state.Moveable;
import capstone.pong.state.Pad;
import capstone.pong.utils.Constants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static capstone.pong.state.Ball.Radius_px;
import static capstone.pong.state.Moveable.Speed_px;
import static capstone.pong.state.Pad.Placement.Bottom;
import static capstone.pong.state.Pad.Placement.Top;
import static capstone.pong.utils.Constants.*;

// FIXME: there are bugs in controlling ball's movement. Test and fix them
public final class GameController
    implements ActionListener {

  public interface Repaintable {
    void repaint();
  }

  private final Set<Moveable> moveables = new HashSet<>();
  private final Repaintable repaintable;
  private final GameStats game;

  private GameController(
      Repaintable repaintable,
      GameStats game
  ) {
    this.repaintable = repaintable;
    this.game = game;
  }

  public static GameController newGameController(
      Repaintable repaint,
      GameStats game
  ) {
    return new GameController(repaint, game);
  }

  public void registerMoveable(Moveable moveable) {
    moveables.add(moveable);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    moveables.forEach(this::move);
    repaintable.repaint();
  }

  private void move(Moveable moveable) {
    if (moveable instanceof Pad) {
      move((Pad) moveable);
    } else {
      move((Ball) moveable);
    }
  }

  private void move(Ball ball) {
    int x = ball.x();
    int y = ball.y();
    int vx = ball.vx();
    int vy = ball.vy();
    int x1 = x + Speed_px * vx;
    int y1 = y + Speed_px * vy;

    if (bounceFromWall(x1)) {
      vx *= -1;
    }
    if (topScores(y1)) {
      game.scored(GameStats.Player.TopPlayer);
      vy *= -1;
    } else if (bottomScores(y1)) {
      game.scored(GameStats.Player.BottomPlayer);
      vy *= -1;
    } else {
      Stream<Pad> pads =
          moveables
              .stream()
              .filter(m -> m instanceof Pad)
              .map(m -> (Pad) m);
      if (bounceFromPad(ball, pads)) {
        vy *= -1;
      }
    }
    if (game.isGameOrRoundEnded()) {
      game.newRound();
      moveables.forEach(Moveable::reset);
    } else {
      ball.bounce(vx, vy);
      ball.move();

    }
  }

  private boolean bounceFromPad(
      Ball ball,
      Stream<Pad> pads
  ) {
    return pads.anyMatch(pad -> {
      int ballX = ball.x();
      int topBallY = ball.y() - Radius_px;
      int bottomBallY = ball.y() + Radius_px;

      int topPadY = Top.y;
      int bottomPadY = Bottom.y;

      return (pad.x() < ballX && (pad.x() + Pad.Width) > ballX) &&
          (topBallY < topPadY || bottomBallY > bottomPadY);
    });
  }

  private boolean bottomScores(int y1) {
    return y1 + Radius_px > H - THICKNESS;
  }

  private boolean topScores(int y1) {
    return y1 < THICKNESS;
  }

  private boolean bounceFromWall(int x1) {
    return (x1 < THICKNESS) ||
        (x1 > W - Radius_px - THICKNESS);
  }

  private void move(Pad pad) {
    if (canMovePad(pad)) {
      pad.move();
    }
  }

  private boolean canMovePad(Pad pad) {
    return pad.x() > (Constants.THICKNESS - pad.vector().vector) &&
        pad.x() < (W - Pad.Width - Constants.THICKNESS - pad.vector().vector);
  }
}
