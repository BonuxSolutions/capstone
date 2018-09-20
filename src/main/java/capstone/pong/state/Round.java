package capstone.pong.state;

import capstone.pong.state.GameStats.Player;

public final class Round {
  public final Score score;
  final boolean ended;

  private Round(
      Score score,
      boolean ended
  ) {
    this.score = score;
    this.ended = ended;
  }

  static Round first() {
    return new Round(
        Score.empty(),
        false
    );
  }

  static Round next(Round curr) {
    return new Round(
        Score.empty(),
        false
    );
  }

  Round scored(Player player) {
    if (ended) {
      return this;
    } else {
      Score newScore = score.scored(player);
      boolean roundEnded = newScore.topPlayerScore == 11 || newScore.bottomPlayerScore == 11;
      return new Round(
          newScore,
          roundEnded
      );
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Round round = (Round) o;

    if (ended != round.ended) return false;
    return score.equals(round.score);
  }

  @Override
  public int hashCode() {
    int result = score.hashCode();
    result = 31 * result + (ended ? 1 : 0);
    return result;
  }
}
