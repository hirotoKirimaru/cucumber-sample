package kirimaru.biz.domain.ecard.card;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {
  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class Battle {

    @MethodSource(value = "battle")
    @ParameterizedTest
    void test_01(Card.CardDiv player1, Card.CardDiv player2, Card.BattleResult result) {
      var player1Card = new Card(player1);
      var player2Card = new Card(player2);

      assertThat(player1Card.battle(player2Card)).isEqualTo(result);
    }


    private Stream<Arguments> battle() {
      return Stream.of(
          Arguments.of(Card.CardDiv.EMPEROR, Card.CardDiv.CITIZEN, Card.BattleResult.WIN)
      );
    }
  }

}
