package capstone.pong.state;

public final class GameStats {
  public enum Player {
    TopPlayer,
    BottomPlayer
  }

  private Score score;
  private Round currentRound;
  private boolean ended;

  public Score score() {
    return score;
  }

  public Round currentRound() {
    return currentRound;
  }

  public boolean ended() {
    return ended;
  }

  private GameStats(
      Score score,
      Round currentRound,
      boolean ended
  ) {
    this.score = score;
    this.currentRound = currentRound;
    this.ended = ended;
  }

  public static GameStats newGame() {
    return new GameStats(
        Score.empty(),
        Round.first(),
        false
    );
  }

  public void scored(Player player) {
    if (!isGameOrRoundEnded()) {
      Score newGameScore = score;
      Round roundAfterScored = currentRound.scored(player);
      if (roundAfterScored.ended) {
        newGameScore = score.scored(player);
      }
      score = newGameScore;
      currentRound = roundAfterScored;
    }
  }

  public boolean isGameOrRoundEnded() {
    return ended || currentRound.ended;
  }

  public void newRound() {
    currentRound = Round.next(currentRound);
  }

  public void endGame() {
    ended = true;
  }

  public int round() {
    return score.topPlayerScore + score.bottomPlayerScore;
  }

  @Override
  public String toString() {
    return score + "\nRound:" + round() + "\n" + currentRound.score.toString();
  }
}
