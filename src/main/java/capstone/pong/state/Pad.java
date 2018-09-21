package capstone.pong.state;

import java.util.Objects;

import static capstone.pong.utils.Constants.*;

public final class Pad
    implements Moveable {
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
  private PadMovementDirection padMovementDirection;
  private int x;
  private final Placement placement;

  private Pad(Placement placement) {
    x = (W - Width) / 2;
    padMovementDirection = PadMovementDirection.StandStill;
    this.placement = placement;
  }

  @Override
  public void reset() {
    x = (W - Width) / 2;
    padMovementDirection = PadMovementDirection.StandStill;
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

  public int y() {
    return placement.y;
  }

  @Override
  public void move() {
    int dx = padMovementDirection.vector * Speed_px;
    x += dx;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pad pad = (Pad) o;
    return x == pad.x &&
        padMovementDirection == pad.padMovementDirection &&
        placement == pad.placement;
  }

  @Override
  public int hashCode() {
    return Objects.hash(padMovementDirection, x, placement);
  }
}
