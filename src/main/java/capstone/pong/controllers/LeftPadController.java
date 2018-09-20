package capstone.pong.controllers;

import capstone.pong.state.Pad;

import java.awt.event.ActionEvent;

public final class LeftPadController
    extends PadController {

  private LeftPadController(
      String actionName,
      Pad paddle,
      int keyEvent
  ) {
    super(actionName, paddle, keyEvent);
  }

  public static LeftPadController leftPad(
      Pad paddle,
      int keyEvent
  ) {
    return new LeftPadController("paddle1-left", paddle, keyEvent
    );
  }

  public static LeftPadController paddleA(
      Pad paddle,
      int keyEvent
  ) {
    return new LeftPadController("paddle2-left", paddle, keyEvent);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    paddle.toLeft();
  }
}
