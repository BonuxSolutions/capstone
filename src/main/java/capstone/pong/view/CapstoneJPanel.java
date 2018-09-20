package capstone.pong.view;

import javax.swing.*;
import java.awt.*;

class CapstoneJPanel extends JPanel {
  protected CapstoneJPanel() {
  }

  static CapstoneJPanel create() {
    return new CapstoneJPanel();
  }

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
}
