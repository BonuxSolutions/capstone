package capstone.pong.state;

import java.awt.*;
import java.util.Objects;

import static capstone.pong.utils.Constants.*;

public class Pad
    implements Moveable, Paintable {
  public enum Placement {
    Top(THICKNESS), Bottom(H - THICKNESS - Heigth);

    public final int y;

    Placement(int y) {
      this.y = y;
    }
  }

  public enum PadMovementDirection {
    MoveLeft(-1), MoveRight(1), StandStill(0);

    public final int vector;

    PadMovementDirection(int vector) {
      this.vector = vector;
    }
  }

  public static final int Width = 100;
  public static final int Heigth = 20;
  private Rectangle pad;
  private PadMovementDirection padMovementDirection;
  private int x;

  private Pad(Placement p) {
    x = (W - Width) / 2;
    pad = new Rectangle(x, p.y, Width, Heigth);
    padMovementDirection = PadMovementDirection.StandStill;
  }

  @Override
  public void reset() {
    x = (W - Width) / 2;
    padMovementDirection = PadMovementDirection.StandStill;
    pad = new Rectangle(x, pad.y, Width, Heigth);
  }

  public static Pad topPad() {
    return new Pad(Placement.Top);
  }

  public static Pad bottomPad() {
    return new Pad(Placement.Bottom);
  }

  public void toLeft() {
    this.padMovementDirection = PadMovementDirection.MoveLeft;
  }

  public void toRight() {
    this.padMovementDirection = PadMovementDirection.MoveRight;
  }

  public PadMovementDirection vector() {
    return padMovementDirection;
  }

  public int x() {
    return x;
  }

  @Override
  public void move() {
    int dx = padMovementDirection.vector * Speed_px;
    x += dx;
    pad.translate(dx, 0);
  }

  @Override
  public void paint(Graphics g) {
    g.setColor(Color.GRAY);
    ((Graphics2D) g).fill(pad);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pad pad1 = (Pad) o;
    return x == pad1.x &&
        Objects.equals(pad, pad1.pad) &&
        padMovementDirection == pad1.padMovementDirection;
  }

  @Override
  public int hashCode() {
    return Objects.hash(pad, padMovementDirection, x);
  }
}
