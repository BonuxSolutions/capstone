package capstone.pong.view;

import capstone.pong.state.Paintable;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public final class GameBoardPanel extends CapstoneJPanel {
  private final Set<Paintable> paintables = new HashSet<>();
  private static final int modifiers = 0;
  private static final boolean released = false;

  private GameBoardPanel() {
  }

  static GameBoardPanel create() {
    return new GameBoardPanel();
  }

  public void addPaintable(Paintable paintable) {
    paintables.add(paintable);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    paintables.forEach(p -> p.paint(g));
  }

  public void add(
      AbstractAction controller,
      int keyCode,
      String actionMapKey
  ) {
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke(keyCode, modifiers, released), actionMapKey);
    getActionMap()
        .put(actionMapKey, controller);
  }
}
