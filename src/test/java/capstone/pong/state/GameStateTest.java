package capstone.pong.state;

import org.junit.jupiter.api.Test;

import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GameStateTest {
  private BinaryOperator<GameStats> combine = (g1, g2) -> g2 = g1;

  @Test
  void newGameTest() {
    GameStats g = GameStats.newGame();

    assertEquals(Score.empty(), g.score());
    assertEquals(Round.first(), g.currentRound());
    assertFalse(g.ended());
  }
}
