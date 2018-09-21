package capstone.pong.view;

import capstone.pong.state.Pad;

import java.awt.*;

public final class PadPainter implements Painter {
  final Pad pad;

  private PadPainter(Pad pad) {
    this.pad = pad;
  }

  public static PadPainter create(Pad pad) {
    return new PadPainter(pad);
  }

  @Override
  public void paint(Graphics g) {
    g.setColor(Color.GRAY);
    g.fillRect(pad.x(), pad.y(), Pad.Width, Pad.Heigth);
  }
}

