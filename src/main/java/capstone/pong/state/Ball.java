package capstone.pong.state;

import java.awt.*;
import java.util.Random;

import static capstone.pong.utils.Constants.H;
import static capstone.pong.utils.Constants.W;

public class Ball
    implements Moveable, Paintable {
  public static final int Radius_px = 20;

  private int x, y;
  private int vx = 0, vy = 0;

  private Ball(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public static Ball ball() {
    return new Ball(W / 2, H / 2);
  }

  public int x() {
    return x;
  }

  public int y() {
    return y;
  }

  public int vx() {
    return vx;
  }

  public int vy() {
    return vy;
  }

  public void start() {
    Random random = new Random();
    vx = random.nextBoolean() ? 1 : -1;
    vy = random.nextBoolean() ? 1 : -1;
  }

  @Override
  public void reset() {
    x = W / 2;
    y = H / 2;
    vx = 0;
    vy = 0;
  }

  @Override
  public void move() {
    x += Speed_px * vx;
    y += Speed_px * vy;
  }

  public void bounce(
      int vx,
      int vy
  ) {
    this.vx = vx;
    this.vy = vy;
  }

  @Override
  public void paint(Graphics g) {
    g.setColor(Color.RED);
    g.fillOval(x, y, Radius_px, Radius_px);
  }
}
