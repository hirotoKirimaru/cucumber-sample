package kirimaru.biz.domain.card;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public class Card {
  public CardDiv cardDiv;

  public Card(int cardDiv) {
    this.cardDiv = CardDiv.get(cardDiv);
  }

  public BattleResult battle(Card card) {
    int result = this.cardDiv.value - card.cardDiv.value;

    if (result == 0) {
      return BattleResult.EVEN;
    } else if (result == -1) {
      return BattleResult.WIN;
    } else if (result == -2) {
      return BattleResult.LOSE;
    } else if (result == 1) {
      return BattleResult.LOSE;
    }
    return BattleResult.WIN;
  }

  @AllArgsConstructor
  enum CardDiv {
    EMPEROR(0),
    CITIZEN(1),
    SLAVE(2);
    int value;

    public static CardDiv get(int value) {
      Optional<CardDiv> first = Arrays.stream(values()).filter(e -> e.value == value).findFirst();
      if (first.isPresent()) {
        return first.get();
      }
      throw new RuntimeException(String.valueOf(value));
    }
  }

  @AllArgsConstructor
  enum BattleResult {
    WIN(0),
    EVEN(1),
    LOSE(2);

    int value;
  }

}
