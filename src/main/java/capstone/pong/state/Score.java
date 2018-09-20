package capstone.pong.state;

public final class Score {
  final int topPlayerScore;
  final int bottomPlayerScore;

  private Score(
      int topPlayerScore,
      int bottomPlayerScore
  ) {
    this.topPlayerScore = topPlayerScore;
    this.bottomPlayerScore = bottomPlayerScore;
  }

  static Score empty() {
    return new Score(0, 0);
  }

  Score scored(GameStats.Player player) {
    Score newScore = empty();
    switch (player) {
      case TopPlayer:
        newScore = new Score(this.topPlayerScore + 1, this.bottomPlayerScore);
        break;
      case BottomPlayer:
        newScore = new Score(this.topPlayerScore, this.bottomPlayerScore + 1);
        break;
    }
    return newScore;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Score score = (Score) o;

    if (topPlayerScore != score.topPlayerScore) return false;
    return bottomPlayerScore == score.bottomPlayerScore;
  }

  @Override
  public int hashCode() {
    int result = topPlayerScore;
    result = 31 * result + bottomPlayerScore;
    return result;
  }

  @Override
  public String toString() {
    return topPlayerScore + " : " + bottomPlayerScore;
  }
}
