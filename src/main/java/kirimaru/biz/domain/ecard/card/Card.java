package kirimaru.biz.domain.ecard.card;

import lombok.AllArgsConstructor;

public abstract class Card {
  public BattleResult battle(Card card) {
    return BattleResult.WIN;
  }

  @AllArgsConstructor
  enum CardDiv {
    EMPEROR(0),
    CITIZEN(1),
    SLAVE(2);
    int value;
  }

  @AllArgsConstructor
  enum BattleResult {
    WIN(0),
    EVEN(1),
    LOSE(2);

    int value;
  }

}
