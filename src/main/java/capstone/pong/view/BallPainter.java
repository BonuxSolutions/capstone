package capstone.pong.view;

import capstone.pong.state.Ball;

import java.awt.*;

import static capstone.pong.state.Ball.Radius_px;

public final class BallPainter implements Painter {
  private final Ball ball;

  private BallPainter(Ball ball) {
    this.ball = ball;
  }

  public static BallPainter create(Ball ball) {
    return new BallPainter(ball);
  }

  public void paint(Graphics g) {
    g.setColor(Color.RED);
    g.fillOval(ball.x(), ball.y(), Radius_px, Radius_px);
  }
}