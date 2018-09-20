package capstone.pong.controllers;

import capstone.pong.state.Pad;

import javax.swing.*;

abstract class PadController
    extends AbstractAction {
  Pad paddle;

  PadController(
      String actionName,
      Pad paddle,
      int keyEvent
  ) {
    super(actionName);
    this.paddle = paddle;
    putValue(MNEMONIC_KEY, keyEvent);
  }
}
