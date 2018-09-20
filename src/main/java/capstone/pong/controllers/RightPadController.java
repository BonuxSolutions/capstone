package capstone.pong.controllers;

import capstone.pong.state.Pad;

import java.awt.event.ActionEvent;

public final class RightPadController extends PadController {
  private RightPadController(
      String actionName,
      Pad paddle,
      int keyEvent
  ) {
    super(actionName, paddle, keyEvent);
  }

  public static RightPadController rightPad(
      Pad paddle,
      int keyEvent
  ) {
    return new RightPadController("paddle1-right", paddle, keyEvent);
  }

  public static RightPadController paddleD(
      Pad paddle,
      int keyEvent
  ) {
    return new RightPadController("paddle2-right", paddle, keyEvent);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    paddle.toRight();
  }
}
